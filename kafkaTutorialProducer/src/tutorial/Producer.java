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
        configs.put("bootstrap.servers", "localhost:9092");	// kafka host �� server ����
        configs.put("acks", "all");							// �����Ϳ� ���� ack�� ��ٸ�
        configs.put("block.on.buffer.full", "true");		// ������ ���� ���ڵ带 ���۸� �� �� ����� �� �ִ� ��ü �޸��� ����Ʈ��
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   // serialize ����
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize ����

        // producer ����
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        // message ����
        for (int i = 0; i < 5; i++) {
        	Date time = new Date();
        	String time1 = format1.format(time);
            String v = time1 + "|" + Thread.currentThread().getStackTrace()[1].getClassName()+"_"+i;
            String a = time1 + "dongeon";
            System.out.println(v);
            producer.send(new ProducerRecord<String, String>("test191031", a));
        }
        
        // ����
        producer.flush();
        producer.close();
	}

}
