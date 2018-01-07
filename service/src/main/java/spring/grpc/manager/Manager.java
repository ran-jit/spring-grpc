package spring.grpc.manager;

import spring.grpc.entry.Entry;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
public interface Manager {

    Entry test(Entry entry) throws Exception;

}
