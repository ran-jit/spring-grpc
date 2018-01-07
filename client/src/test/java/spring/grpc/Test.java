package spring.grpc;

import spring.grpc.client.GrpcClient;
import spring.grpc.client.RestClient;
import spring.grpc.entry.Entry;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
public class Test {

    public static void main(String[] args) {
        Entry entry = Entry.builder().name("ranjith").build();

        testRestClient(entry);
        testGrpcClient(entry);
    }

    private static void testRestClient(Entry entry) {
        RestClient client = new RestClient("http://localhost:8000");
        client.test(entry);
    }

    private static void testGrpcClient(Entry entry) {
        GrpcClient client = new GrpcClient("localhost:8500");
        client.test(entry);
    }

}
