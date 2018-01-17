package spring.grpc.manager.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import spring.grpc.entry.Entry;
import spring.grpc.exception.CustomException;
import spring.grpc.manager.Manager;
import spring.grpc.statuscode.CustomStatusCode;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
@Service
public class ManagerImpl implements Manager {

    @Override
    public Entry test(Entry entry) throws Exception {
        if (entry == null) {
            throw new CustomException(CustomStatusCode.NULL_ENTRY);
        }
        entry.setId(UUID.randomUUID());
        return entry;
    }

}
