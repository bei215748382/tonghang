package com.tonghang.server.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes({ String[].class })
@MappedJdbcTypes({ JdbcType.VARCHAR })
public class StringToListHandler implements TypeHandler<String[]> {

    @Override
    public String[] getResult(ResultSet arg0, String arg1) throws SQLException {
        String strs = arg0.getString(arg1);
        if (strs != null) {
            return strs.split(",");
        }
        return null;

    }

    @Override
    public String[] getResult(ResultSet arg0, int arg1) throws SQLException {
        return null;
    }

    @Override
    public String[] getResult(CallableStatement arg0, int arg1)
            throws SQLException {
        return null;
    }

    @Override
    public void setParameter(PreparedStatement arg0, int arg1, String[] arg2,
            JdbcType arg3) throws SQLException {
        String s = join(",", arg2);
        arg0.setString(arg1, s);
    }

    private String join(String join, String[] strAry) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strAry.length; i++) {
            if (i == (strAry.length - 1)) {
                sb.append(strAry[i]);
            } else {
                sb.append(strAry[i]).append(join);
            }
        }

        return new String(sb);
    }
}
