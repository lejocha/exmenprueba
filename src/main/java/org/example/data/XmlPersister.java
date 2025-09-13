package org.example.data;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XmlPersister {
    private static XmlPersister instance;
    private static final String DATA_FILE = "src/main/resources/fut.xml";

    private XmlPersister() {}

    public static XmlPersister instance() {
        if (instance == null) {
            instance = new XmlPersister();
        }
        return instance;
    }

    public Data load() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Data.class);
        File file = new File(DATA_FILE);

        if (file.exists()) {
            try (FileInputStream is = new FileInputStream(file)) {
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (Data) unmarshaller.unmarshal(is);
            }
        } else {
            return new Data();
        }
    }

    public void store(Data data) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Data.class);
        try (FileOutputStream os = new FileOutputStream(DATA_FILE)) {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, os);
            os.flush();
        }
    }
}