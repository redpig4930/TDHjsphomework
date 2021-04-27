package Jdbc;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author huzhenyu
 * @Description 增删查改类
 * @Project Name:test
 * @File_Name: CRUDtuser
 * @Package_Name:  tdh.homework
 * Modification History:
 * @Date @TIME      @Author      @Version  Description
 * ------------------------------------------------------------------
 * 2021/04/17 14:40  huzhenyu     1.0        1.0 Version
 * 2021/04/20 15:18  huzhenyu     1.01       一、更改类名、变量名，使其符合命名规范;
 *                                           二、修改create方法，不直接使用数字下标来指代通配符;
 */
public class CrudTuser {
    /**
     * 性别代码-性别名称map
     */
    public static Map<String,String> map = new HashMap();

    /**
     * 根据用户代码删除相应记录
     * @param userId 用户代码
     * @throws Exception 异常信息
     */
    public static void delete(String userId)throws Exception{
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //建立连接
            conn = JdbcUtils.getConnection();
            //创建语句
            pst = conn.prepareStatement("delete from T_USER where YHDM=?");
            pst.setString(1, userId);
            //执行语句
            int i = pst.executeUpdate();
            if(i==1) System.out.println("删除记录YHDM="+userId);
        } finally {
            JdbcUtils.close(rs,pst,conn);
        }
    }

    /**
     * 插入记录
     * @param YHDM 用户代码
     * @param DWDM 单位代码
     * @param YHID 用户id
     * @param YHXM 用户姓名
     * @param YHKL 用户口令
     * @param YHXB 用户性别
     * @param YHBM 用户部门
     * @param CSRQ 出生日期
     * @param DJSJ 登记时间
     * @throws Exception 一异常信息
     */
    public static int create(String YHDM, String DWDM, String YHID, String YHXM, String YHKL, String YHXB, String YHBM, String CSRQ, String DJSJ)throws Exception{
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        //values数组保存要插入的字段，且顺序与表相同
        String[] values = {YHDM, DWDM, YHID, YHXM, YHKL, YHXB, YHBM, CSRQ, DJSJ};
        try {
            //建立连接
            conn = JdbcUtils.getConnection();
            //创建语句
            pst = conn.prepareStatement("insert into T_USER (YHDM, DWDM, YHID, YHXM, YHKL, YHXB, YHBM, CSRQ, DJSJ) " +
                    "values (?,?,?,?,?,?,?,?,?)");
            for(int i=1;i<= values.length;i++){
                pst.setString(i,values[i-1]);
            }
            //执行语句
            pst.executeUpdate();
            return 1;
            //System.out.println("插入记录："+YHDM+","+DWDM+","+YHID+","+YHXM+","+YHKL+","+YHXB+","+YHBM+","+CSRQ+","+DJSJ);
        }catch (MySQLIntegrityConstraintViolationException e){
            System.out.println("主键重复");
            return 0;
        }
        finally {
            JdbcUtils.close(rs,pst,conn);
        }
    }

    /**
     * 更新用户姓名
     * @param userId 用户id
     * @param updateValue 更新值
     * @throws Exception 异常信息
     */
    public static void updateName(String userId,String updateValue)throws Exception{
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //建立连接
            conn = JdbcUtils.getConnection();
            //创建语句
            pst = conn.prepareStatement("update T_USER set YHXM = ? where YHDM = ?");
            pst.setString(1,updateValue);
            pst.setString(2,userId);
            //执行语句
            int i = pst.executeUpdate();
            if(i==1) System.out.println("更新姓名为"+updateValue);
        } finally {
            JdbcUtils.close(rs,pst,conn);
        }
    }

    /**
     * 查询用户记录
     * @param userId 用户id
     * @return map 用户信息
     * @throws Exception
     */
    public static List<Map<String,String>> readByIdName(String userId) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try{
            //建立连接
            conn = JdbcUtils.getConnection();
            //创建语句
            pst = conn.prepareStatement("select YHID, YHXM, YHKL, YHXB, YHBM, CSRQ, PXH from T_USER where YHID =? or YHXM=?");
            pst.setString(1,userId);
            pst.setString(2,userId);
            //执行语句
            rs = pst.executeQuery();
            //处理结果
            while (rs.next()){
                Map<String,String> map = new HashMap();
                map.put("YHID",rs.getString("YHID"));
                map.put("YHXM",rs.getString("YHXM"));
                map.put("YHKL",rs.getString("YHKL"));
                map.put("YHXB",gendermap(rs.getString("YHXB")));
                map.put("YHBM", DepartMap.departMap(rs.getString("YHBM")));
                map.put("CSRQ",rs.getString("CSRQ"));
                map.put("PXH",rs.getString("PXH"));
                list.add(map);
            }
        }finally {
            JdbcUtils.close(rs,pst,conn);
        }
        return list;
    }

    /**
     * 将性别代码转换成性别名称
     * @param CODE 性别代号
     * @return 性别名称（男，女，其他）
     * @throws Exception 异常信息
     */
    public static String gendermap(String CODE) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        if(map.isEmpty()){
            try {
                // 建立连接
                conn = JdbcUtils.getConnection();
                // 创建语句
                pst = conn.prepareStatement("select CODE,MC from TS_BZDM where BT='性别'");
                // 执行语句
                rs = pst.executeQuery();
                // 处理结果
                while (rs.next()) {
                    map.put(rs.getString("CODE"),rs.getString("MC"));
                }
            }
            finally {
                JdbcUtils.close(rs, pst, conn);
            }
        }
        return (String)map.get(CODE);
    }

    public static List<Map<String,String>> readByDepart(String departId) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try{
            //建立连接
            conn = JdbcUtils.getConnection();
            //创建语句
            pst = conn.prepareStatement("select YHID, YHXM, YHKL, YHXB, YHBM, CSRQ, PXH from T_USER where YHBM=?");
            pst.setString(1,departId);
            //执行语句
            rs = pst.executeQuery();
            //处理结果
            while (rs.next()){
                Map<String,String> map = new HashMap();
                map.put("YHID",rs.getString("YHID"));
                map.put("YHXM",rs.getString("YHXM"));
                map.put("YHKL",rs.getString("YHKL"));
                map.put("YHXB",gendermap(rs.getString("YHXB")));
                map.put("YHBM", DepartMap.departMap(rs.getString("YHBM")));
                map.put("CSRQ",rs.getString("CSRQ"));
                map.put("PXH",rs.getString("PXH"));
                list.add(map);
            }
        }finally {
            JdbcUtils.close(rs,pst,conn);
        }
        return list;
    }
}
