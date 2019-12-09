package com.jfeat.am.module.meta.constant;

/**
 * 规定的实体字段类型
 */
public enum EntityFieldType {

    NUMBER,STRING,DATE;

    /**
     * 判断输入类型是否是规定实体字段类型
     * @param entityFieldType
     * @return
     */
    public static boolean isEntityFieldType(String entityFieldType) {
        for (EntityFieldType type : EntityFieldType.values()) {
            if (type.toString().equals(entityFieldType)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
