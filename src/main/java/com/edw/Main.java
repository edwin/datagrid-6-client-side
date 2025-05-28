package com.edw;

import com.edw.marshaller.GenMdAccountEntityMarshaller;
import com.edw.model.GenMdAccountEntity;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.protostream.FileDescriptorSource;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <pre>
 *  com.edw.Main
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 28 May 2025 14:44
 */
public class Main {

    private RemoteCache remoteCache;

    public Main() {
    }

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        // generate the data first
        main.generateData();

       // then do the query
        main.query();
    }

    private void generateData() {
        try {
            // connect to the server
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.addServer()
                    .host("127.0.0.1")
                    .port(Integer.parseInt("11222"))
                    .marshaller(new ProtoStreamMarshaller());

            RemoteCacheManager manager = new RemoteCacheManager(builder.build());
            remoteCache = manager.getCache("GEN_MD_ACCOUNT");

            // create the protobuf file
            RemoteCache<String, String> metadataCache =
                    manager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);

            String schemaFileContents = readProtoFileFromResources("default/GenMdAccountEntity.proto");
            metadataCache.put("GenMdAccountEntity.proto", schemaFileContents);

            // register serialization
            SerializationContext ctx = ProtoStreamMarshaller.getSerializationContext(manager);
            ctx.registerProtoFiles(FileDescriptorSource.fromResources("/default/GenMdAccountEntity.proto"));
            ctx.registerMarshaller(new GenMdAccountEntityMarshaller());

            // put some data
            for (int i = 0; i < 1000; i++) {
                remoteCache.put(i, new GenMdAccountEntity(new Random().nextLong(), ""+i, 0l, "111",
                        "A", "00", "aaa", "2222",
                        "A", "00", "aaa", "2222",
                        null, "00", null, "2222",
                        null, "00", null, "2222"
                ));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void query() {
        try {
            // connect to the server
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.addServer()
                    .host("127.0.0.1")
                    .port(Integer.parseInt("11222"))
                    .marshaller(new ProtoStreamMarshaller());

            RemoteCacheManager manager = new RemoteCacheManager(builder.build());
            remoteCache = manager.getCache("GEN_MD_ACCOUNT");

            // register serialization
            SerializationContext ctx = ProtoStreamMarshaller.getSerializationContext(manager);
            ctx.registerProtoFiles(FileDescriptorSource.fromResources("/default/GenMdAccountEntity.proto"));
            ctx.registerMarshaller(new GenMdAccountEntityMarshaller());

            QueryFactory qf = Search.getQueryFactory(remoteCache);
            for (int i = 0; i < 1000; i++) {
                Query query = qf.from(GenMdAccountEntity.class)
                        .having("ACCOUNT_CODE").eq(""+i)
                        .toBuilder()
                        .startOffset(0)
                        .maxResults(Integer.MAX_VALUE)
                        .build();

                Long timestamp = System.currentTimeMillis();
                System.out.println(query.list()+ " ~ " +(System.currentTimeMillis() - timestamp));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String readProtoFileFromResources(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found on classpath: " + fileName);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

}
