package com.dmz.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Integer [].class) //javabean中成员变量类型
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyArraysHandler extends BaseTypeHandler<Integer []> {
    ObjectMapper objectMapper = new ObjectMapper();

    //输入映射的过程 → 为预编译的sql语句提供参数

    @SneakyThrows
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int index, Integer[] integers, JdbcType jdbcType) throws SQLException {

        System.out.println("Index"+index);
        String value = objectMapper.writeValueAsString(integers);
        preparedStatement.setString(index,value);
    }

    //get方法 → 结果集的封装
    @SneakyThrows
    @Override
    public Integer[] getNullableResult(ResultSet resultSet, String s) throws SQLException {

        System.out.println("String s"+s);
        String result = resultSet.getString(s);
        System.out.println("result"+result);
        return parseResult(result);
    }

    @SneakyThrows
    @Override
    public Integer[] getNullableResult(ResultSet resultSet, int i) throws SQLException {
        System.out.println("i"+i);

        String result = resultSet.getString(i);
        System.out.println("result"+result);
        return parseResult(result);
    }

    @SneakyThrows
    @Override
    public Integer[] getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
        String result = callableStatement.getString(index);
        System.out.println("result"+result);
        return parseResult(result);
    }

    private Integer [] parseResult(String result) throws JsonProcessingException {
        return objectMapper.readValue(result,Integer[].class);
    }
}
