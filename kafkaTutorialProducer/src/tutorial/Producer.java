package tutorial;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class Producer {

	public static void main(String[] args)  throws IOException  {
		// TODO Auto-generated method stub
        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");	// kafka host 및 server 설정
        configs.put("acks", "all");							// 데이터에 대한 ack를 기다림
        configs.put("block.on.buffer.full", "true");		// 서버로 보낼 레코드를 버퍼링 할 때 사용할 수 있는 전체 메모리의 바이트수
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   // serialize 설정
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정

        // producer 생성
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        // message 전달
        for (int i = 0; i < 5; i++) {
        	Date time = new Date();
        	String time1 = format1.format(time);
            String v = time1 + "|" + Thread.currentThread().getStackTrace()[1].getClassName()+"_"+i;
            String a = time1 + "dongeon";
            System.out.println(v);
            producer.send(new ProducerRecord<String, String>("test191031", a));
        }
        
        // 종료
        producer.flush();
        producer.close();
	}

}
