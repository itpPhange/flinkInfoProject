package
        com.zcycsoft.demo.app.sic.api;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.ververica.cdc.debezium.DebeziumDeserializationSchema;
import io.debezium.data.Envelope;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.List;

/**
 * @author wyi
 * @date 2022/8/18 10:32
 * @description 这是一个demo，测试flink-cdc连接mysql的反序列化类
 */
public class TestRuleDeserialization implements DebeziumDeserializationSchema<JSONObject> {

    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<JSONObject> collector) throws Exception {
        //获取主题
        String topic = sourceRecord.topic();
        String[] arr = topic.split("\\.");
        String db = arr[1];
        String tableName = arr[2];

        System.out.println(arr[1]);
        System.out.println(arr[2]);

        //获取操作类型 READ DELETE UPDATE CREATE
        Envelope.Operation operation = Envelope.operationFor(sourceRecord);
        //获取值信息并转换为Struct类型
        Struct value = (Struct) sourceRecord.value();

        System.out.println("value:"+value);
        //获取变化后的数据
        Struct after = value.getStruct("after");

        //创建JSON对象用于存储数据信息
        JSONObject data = new JSONObject();
        for (Field field : after.schema().fields()) {
            Object o = after.get(field);
            data.put(field.name(), o);
        }

        //创建JSON对象用于封装最终返回值数据信息
        JSONObject result = new JSONObject();
        result.put("operation", operation.toString().toLowerCase());
        result.put("data", data);
        result.put("database", db);
        result.put("table", tableName);

        //发送数据至下游
        collector.collect(result);


    }

    @Override
    public TypeInformation<JSONObject> getProducedType() {

        return TypeInformation.of(JSONObject.class);
    }
}

