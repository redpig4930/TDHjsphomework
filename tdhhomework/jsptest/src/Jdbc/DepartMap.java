package Jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author huzhenyu
 * @Description 部门代码转换类
 * @Project Name:test
 * @File_Name: Departmap
 * @Package_Name:  tdh.homework
 * Modification History:
 *  * @Date @TIME      @Author      @Version  Description
 *  * ------------------------------------------------------------------
 *  * 2021/04/16 19:23  huzhenyu     1.0        1.0 Version
 *  * 2021/04/20 15:22  huzhenyu     1.01       更改了类名、变量名，使其符合命名规范
 */
public class DepartMap {
    /**
     *部门代码-部门名称map
     */
    public static Map<String,String> idNameMap = new HashMap();

    /**
     * 通过部门代码返回部门名称
     * @param deId 部门代码
     * @return String 部门名称
     * @throws SQLException 异常信息
     * @throws ClassNotFoundException 异常信息
     */
    public static String departMap(String deId) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        //检查map是否为空,为空则查询数据添加到map。否则直接使用map，不查询数据库
        if(idNameMap.isEmpty()){
            try {
                // 建立连接
                conn = JdbcUtils.getConnection();
                // 创建语句
                pst = conn.prepareStatement("select BMDM,BMMC from T_DEPART");
                // 执行语句
                rs = pst.executeQuery();
                // 处理结果
                while (rs.next()) {
                    idNameMap.put(rs.getString("BMDM"),rs.getString("BMMC"));
                }
            }
            finally {
                JdbcUtils.close(rs, pst, conn);
            }
        }
        if(idNameMap.get(deId)!=null)
            return (String) idNameMap.get(deId);
        else
            return "没有找到该部门";
    }
}
