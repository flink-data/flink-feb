package flink1;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class FlatMapSentence {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(2);
        DataStreamSource<String> text = environment.fromElements("Hello Everyone", "Everyone is awesome");
        text.flatMap(new MyFlatMapper()).print();
        environment.execute();
    }

    public static class MyFlatMapper implements FlatMapFunction<String, String> {
        @Override
        public void flatMap(String value, Collector<String> out) throws Exception {
            for (String word : value.split(" ")) {
                out.collect(word);
            }
        }
    }
}
