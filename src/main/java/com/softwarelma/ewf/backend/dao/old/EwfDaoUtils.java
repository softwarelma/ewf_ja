package com.softwarelma.ewf.backend.dao.old;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;
import com.softwarelma.ewf.backend.old.EwfBackend;

public abstract class EwfDaoUtils {

    public static String FOLDER = "/org/company/workspaces/neon-1a/ewf/src/main/resources/daoData";

    /**
     * Without LF
     * 
     * {-1}: null - {-2}: no LF - {0}: LF is {0}
     * 
     * {1}attr1{-1}{1}attr2{-2}abc{1}attr3{0}ab{0}c
     */
    public static <T> T newInstance(EwfBackend backend, Class<T> clazz, String source) throws EpeAppException {
        String[] nameCodeValue = retrieveNameCodeValue(source);
        // String name = nameCodeValue[0];
        String code = nameCodeValue[1];
        String valueStr = nameCodeValue[2];
        valueStr = valueStr.replace(code, "\n");
        return newInstanceWithLF(backend, clazz, valueStr);
    }

    private static String[] retrieveNameCodeValue(String source) throws EpeAppException {
        EpeAppUtils.checkNull("source", source);
        int ind1 = source.indexOf("{");
        String name = source.substring(0, ind1);
        int ind2 = source.indexOf("}") + 1;
        String code = source.substring(ind1, ind2);
        String valueStr = source.substring(ind2);
        return new String[] { name, code, valueStr };
    }

    /**
     * {-1}: null - {-2}: no LF - {0}: LF is {0}
     * 
     * attr1{-1}
     * 
     * attr2{-2}abc
     * 
     * attr3{0}ab{0}c
     */
    private static <T> T newInstanceWithLF(EwfBackend backend, Class<T> clazz, String source) throws EpeAppException {
        EpeAppUtils.checkNull("clazz", clazz);
        EpeAppUtils.checkNull("source", source);
        String excepMessage = "";

        try {
            String[] arrayAttribute = source.split("\n");
            Method[] arrayMethod = clazz.getMethods();
            T obj = clazz.newInstance();

            for (String att : arrayAttribute) {
                String[] nameCodeValue = retrieveNameCodeValue(att);
                String name = nameCodeValue[0];
                String code = nameCodeValue[1];
                String valueStr = nameCodeValue[2];
                excepMessage = "Class=" + clazz.getName() + ", attribute=" + att + ", value=" + valueStr;
                Object valueObj;
                Method method = findMethod(arrayMethod, "set" + name.substring(0, 1).toUpperCase() + name.substring(1));
                Class<?>[] arrayParamTypes = method.getParameterTypes();
                EpeAppUtils.checkEmptyArray("arrayParamTypes", arrayParamTypes);
                Class<?> clazzParam = arrayParamTypes[0];

                if (code.equals("{-1}")) {
                    valueObj = null;
                } else if (code.equals("{-2}")) {
                    valueObj = newInstanceParam(backend, clazzParam, valueStr);
                } else if (code.contains("-")) {
                    throw new EpeAppException("Unknown code: " + code);
                } else {
                    valueStr = valueStr.replace(code, "\n");
                    valueObj = newInstanceParam(backend, clazzParam, valueStr);
                }

                method.invoke(obj, valueObj);
            }

            return obj;
        } catch (InstantiationException e) {
            throw new EpeAppException(excepMessage, e);
        } catch (IllegalAccessException e) {
            throw new EpeAppException(excepMessage, e);
        } catch (IllegalArgumentException e) {
            throw new EpeAppException(excepMessage, e);
        } catch (InvocationTargetException e) {
            throw new EpeAppException(excepMessage, e);
        }
    }

    public static String retrieveDaoClassName(Class<?> clazzParam) throws EpeAppException {
        EpeAppUtils.checkNull("clazzParam", clazzParam);

        if (!clazzParam.getName().contains(".EwfEntity")) {
            throw new EpeAppException("Not ewf class: " + clazzParam.getName());
        }

        String className = clazzParam.getName();
        className = className.substring(className.lastIndexOf(".") + 1);
        String daoClassName = "com.softwarelma.ewf.backend.dao.EwfDao" + className.substring(9);
        return daoClassName;
    }

    private static Object newInstanceParam(EwfBackend backend, Class<?> clazzParam, String valueStr)
            throws EpeAppException {
        EpeAppUtils.checkNull("backend", backend);
        EpeAppUtils.checkNull("clazzParam", clazzParam);
        EpeAppUtils.checkNull("valueStr", valueStr);
        Object valueObj;

        if (clazzParam.getName().contains(".EwfEntity")) {
            String daoClassName = retrieveDaoClassName(clazzParam);
            Long id = Long.parseLong(valueStr);
            valueObj = backend.read(daoClassName, id);
        } else if (clazzParam.equals(String.class)) {
            valueObj = valueStr;
        } else if (clazzParam.equals(Long.class)) {
            valueObj = Long.parseLong(valueStr);
        } else {
            throw new EpeAppException("Unknown clazzParam: " + clazzParam.getName());
        }

        return valueObj;
    }

    public static Method findMethod(Method[] arrayMethod, String methodName) throws EpeAppException {
        EpeAppUtils.checkNull("arrayMethod", arrayMethod);
        EpeAppUtils.checkEmpty("methodName", methodName);

        for (Method method : arrayMethod) {
            EpeAppUtils.checkNull("method", method);

            if (method.getName().equals(methodName)) {
                return method;
            }
        }

        EpeAppUtils.checkNull("method", null);
        return null;
    }

    public static List<Method> findListMethod(Method[] arrayMethod, List<Map.Entry<String, Object>> listAttNameAndValue,
            String getOrSet) throws EpeAppException {
        EpeAppUtils.checkNull("listAttNameAndValue", listAttNameAndValue);
        List<Method> listMethod = new ArrayList<>();
        String methodName;
        String attName;

        for (Map.Entry<String, Object> attNameAndValue : listAttNameAndValue) {
            EpeAppUtils.checkNull("attNameAndValue", attNameAndValue);
            attName = attNameAndValue.getKey();
            EpeAppUtils.checkEmpty("attName", attName);
            methodName = getOrSet + attName.substring(0, 1).toUpperCase() + attName.substring(1);
            listMethod.add(findMethod(arrayMethod, methodName));
        }

        return listMethod;
    }

    // TODO avoid reflection
    public static <T extends EwfEntityAbstract> List<Object> invoke(List<Method> listMethod, T entity)
            throws EpeAppException {
        EpeAppUtils.checkNull("listMethod", listMethod);
        EpeAppUtils.checkNull("entity", entity);
        List<Object> listObject = new ArrayList<>();
        String prefix = "Inoking on class " + entity.getClass().getName() + " the method ";
        String excepMessage;
        Object obj;

        for (Method method : listMethod) {
            EpeAppUtils.checkNull("method", method);
            excepMessage = prefix + method.getName();

            try {
                obj = method.invoke(entity);
                listObject.add(obj);
            } catch (IllegalAccessException e) {
                throw new EpeAppException(excepMessage, e);
            } catch (IllegalArgumentException e) {
                throw new EpeAppException(excepMessage, e);
            } catch (InvocationTargetException e) {
                throw new EpeAppException(excepMessage, e);
            }
        }

        return listObject;
    }

    public static boolean isValidEnity(List<Map.Entry<String, Object>> listAttNameAndValue, List<Object> listValue)
            throws EpeAppException {
        EpeAppUtils.checkNull("listAttNameAndValue", listAttNameAndValue);
        EpeAppUtils.checkNull("listValue", listValue);
        EpeAppUtils.checkEquals("listAttNameAndValue.size", "listValue.size", listAttNameAndValue.size(),
                listValue.size());
        Map.Entry<String, Object> attNameAndValue;
        Object valueEntity, valueFilter;

        for (int i = 0; i < listAttNameAndValue.size(); i++) {
            attNameAndValue = listAttNameAndValue.get(i);
            EpeAppUtils.checkNull("attNameAndValue", attNameAndValue);
            valueFilter = attNameAndValue.getValue();
            valueEntity = listValue.get(i);

            if (valueFilter != null && valueEntity != null && valueFilter instanceof Long
                    && valueEntity instanceof EwfEntityAbstract && attNameAndValue.getKey().startsWith("id")) {
                valueEntity = ((EwfEntityAbstract) valueEntity).getId();
            }

            if (valueFilter == null && valueEntity == null) {
                continue;
            } else if (valueFilter == null && valueEntity != null) {
                return false;
            } else if (valueFilter != null && valueEntity == null) {
                return false;
            } else if (valueFilter.equals(valueEntity)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Without LF
     * 
     * {-1}: null - {-2}: no LF - {0}: LF is {0}
     * 
     * {1}attr1{-1}{1}attr2{-2}abc{1}attr3{0}ab{0}c
     * 
     * @throws EpeAppException
     */
    public static String toString(Object obj) throws EpeAppException {
        if (obj == null) {
            return "{-1}";
        }

        StringBuilder sb = new StringBuilder();
        String sep = "";
        String excepMessage = "";

        try {
            Class<?> clazz = obj.getClass();
            excepMessage = "Class=" + clazz.getName() + ", value=" + (obj + "").replace("\n", "");

            if (!clazz.getName().contains(".EwfEntity")) {
                throw new EpeAppException("Unknown clazz: " + clazz.getName());
            }

            Method[] arrayMethod = clazz.getMethods();

            for (Method method : arrayMethod) {
                if (!method.getName().startsWith("get")) {
                    continue;
                } else if (method.getName().contains("getClass")) {
                    continue;
                }

                String attName = method.getName().substring(3);
                attName = attName.substring(0, 1).toLowerCase() + attName.substring(1);
                Object objAtt = method.invoke(obj);
                sb.append(sep);
                sep = "\n";
                sb.append(attName);
                Class<?> clazzAtt = objAtt == null ? null : objAtt.getClass();

                if (objAtt == null) {
                    sb.append("{-1}");
                } else if (clazzAtt.getName().contains(".EwfEntity")) {
                    EwfEntityAbstract entity = (EwfEntityAbstract) objAtt;
                    sb.append("{-2}");
                    sb.append((Long) entity.getId());
                } else if (clazzAtt.equals(String.class)) {
                    String valueStr = (String) objAtt;

                    if (valueStr.contains("\n")) {
                        String ncs = EpeAppUtils.getNotContainedString(valueStr);
                        sb.append(ncs);
                        valueStr = valueStr.replace("\n", ncs);
                        sb.append(valueStr);
                    } else {
                        sb.append("{-2}");
                        sb.append(valueStr);
                    }
                } else if (clazzAtt.equals(Long.class)) {
                    sb.append("{-2}");
                    sb.append((Long) objAtt);
                } else {
                    throw new EpeAppException("Unknown clazzAtt: " + clazzAtt.getName());
                }
            }

            String str = sb.toString();
            String ncs = EpeAppUtils.getNotContainedString(str);
            str = ncs + str.replaceAll("\n", ncs);
            return str;
        } catch (IllegalAccessException e) {
            throw new EpeAppException(excepMessage, e);
        } catch (InvocationTargetException e) {
            throw new EpeAppException(excepMessage, e);
        }
    }

}
