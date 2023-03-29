//package
//        com.zcycsoft.demo.app.sic.api;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.ververica.cdc.connectors.mysql.MySQLSource;
//import com.alibaba.ververica.cdc.debezium.DebeziumSourceFunction;
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//
//import java.util.Properties;
//
///**
// * @author wyi
// * @date 2022/8/18 11:06
// * @description
// */
//public class flinkcdcTest {
//    public static void main(String[] args) throws Exception{
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        Properties pops = new Properties();
//        pops.setProperty("debezium.snapshot.locking.mode", "none");
//        String[] databaseList = {"test"};
//        String[] tableList = {"test"};
//        DebeziumSourceFunction<JSONObject> mysqlSource = MySQLSource.<JSONObject>builder()
//                .hostname("192.168.31.37")
//                .port(55003)
//                .serverTimeZone("Asia/Shanghai")
//                .username("root")
//                .password("zcycsoft@mysql@.123")
//                .databaseList(databaseList)
//                .tableList(tableList)
//                .deserializer(new TestRuleDeserialization())
//                .build();
//        SingleOutputStreamOperator<Object> map = env.addSource(mysqlSource).map(new MapFunction<JSONObject, Object>() {
//            @Override
//            public Object map(JSONObject jsonObject) throws Exception {
//                return jsonObject.toString();
//            }
//        });
//        map.print();
//        map.disableChaining();
//        env.execute("CdcMysqlSource");
//    }
//}
//
