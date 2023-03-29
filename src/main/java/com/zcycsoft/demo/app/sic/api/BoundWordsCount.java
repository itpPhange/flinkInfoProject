package
        com.zcycsoft.demo.app.sic.api;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;


/**
 * @author 蒋鹏
 * @version V1.0
 * @Title: BoundesWordsCount
 * @Package com.zcycsoft.demo.app.sic.api
 * @Description:
 * @Copyright: Copyright (c) 2021
 * @Company:四川志诚元创信息科技有限公司
 * @date 2023/3/29 10:01
 * @description:
 */
public class BoundWordsCount {
    public static void main(String[] args) throws Exception {
        //1、构建流处理环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //2、开始获取文件
        DataStreamSource<String> lineDSS = env.readTextFile("input/words.txt");
        //3、开始解析流文件
        SingleOutputStreamOperator<Tuple2<String, Long>> returns = lineDSS
                .flatMap((String line, Collector<String> words) -> {
                    Arrays.stream(line.split(" ")).forEach(words::collect);
                })
                .returns(Types.STRING)
                .map(word -> Tuple2.of(word, 1L))
                .returns(Types.TUPLE(Types.STRING, Types.LONG));
        //4、进行分区操作
        KeyedStream<Tuple2<String, Long>, String> tuple2StringKeyedStream = returns.keyBy(stringLongTuple2 -> stringLongTuple2.f0);
        //5、计算集合
        tuple2StringKeyedStream.sum(1);

        tuple2StringKeyedStream.print();

        env.execute();

    }
}