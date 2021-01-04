import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world !");
        try {
//            var bytes = toByteArray("D:\\private\\roo\\1229\\fff_fff_fff");
//            var unpack =  ChecksumUnpack(bytes);
//            SaveToJson(unpack);

//            var bytes1 = toByteArray("D:\\private\\roo\\1229\\123456789.robytes");
//            var unpack1 =  DatabaseUnpack(bytes1);
//            SaveToJson(unpack1);

//            var bbb = GetFile("D:\\private\\roo\\1229\\file.json");
//            var sss = ChecksumPack(bbb);
//            writeBytesToFile("D:\\private\\roo\\1229\\fff_fff_fff", sss);

            var bbb1 = GetDatabaseFile("D:\\private\\roo\\1229\\database.json");
            var sss1 = DatabasePack(bbb1, false);
            writeBytesToFile("D:\\private\\roo\\1229\\123456789.robytes", sss1);
            System.out.println("DDDDD");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //var byte =
    }

    private static void writeBytesToFile(String fileOutput, byte[] bytes)
            throws IOException {

        try (FileOutputStream fos = new FileOutputStream(fileOutput)) {
            fos.write(bytes);
        }

    }

    public static Checksum GetFile(String fileName)
    {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(fileName));

            // convert JSON string to User object
            Checksum result = gson.fromJson(reader, Checksum.class);

            // close reader
            reader.close();

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Database GetDatabaseFile(String fileName)
    {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(fileName));

            // convert JSON string to User object
            Database result = gson.fromJson(reader, Database.class);

            // close reader
            reader.close();

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    public static void SaveToJson(Database model) {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("D:\\private\\roo\\1229\\database123.json"));

            // convert user object to JSON file
            gson.toJson(model, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void SaveToJson(Checksum model) {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("D:\\private\\roo\\1229\\file123.json"));

            // convert user object to JSON file
            gson.toJson(model, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    public static Database DatabaseUnpack(byte[] paramArrayOfByte)
    {
        try {
            Database localDatabase = new Database();
            if (paramArrayOfByte.length == 0)
                return localDatabase;
            Buffer.Payload localPayload = new Buffer.Payload();
            localDatabase.count = Buffer.GetInt(paramArrayOfByte, localPayload);
            int i = 0;
            while (i < localDatabase.count)
            {
                DatabaseItem localDatabaseItem = new DatabaseItem();
                localDatabaseItem.id = Convert.Unsigned(Buffer.GetInt(paramArrayOfByte, localPayload));
                localDatabaseItem.text = Buffer.GetString(paramArrayOfByte, localPayload);
                localDatabase.items.put(Long.valueOf(localDatabaseItem.id), localDatabaseItem);
                i += 1;
            }
            localDatabase.unknown1 = Buffer.GetInt(paramArrayOfByte, localPayload);
            localDatabase.unknown2 = Buffer.GetInt(paramArrayOfByte, localPayload);
            return localDatabase;
        }catch (Exception ex) {
            System.out.printf(ex.getMessage());
        }

        return null;
    }

    public static byte[] DatabasePack(Database paramDatabase, boolean paramBoolean)
    {
        byte[] arrayOfByte = new byte[0];
        var localObject1 = arrayOfByte;
        try
        {
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localObject1 = arrayOfByte;
            localByteArrayOutputStream.write(Convert.IntToBytes(paramDatabase.items.size(), true));
            localObject1 = arrayOfByte;
            Iterator localIterator = paramDatabase.items.values().iterator();
            while (true)
            {
                localObject1 = arrayOfByte;
                if (!localIterator.hasNext())
                    break;
                localObject1 = arrayOfByte;
                DatabaseItem localDatabaseItem = (DatabaseItem)localIterator.next();
                localObject1 = arrayOfByte;
                String str = localDatabaseItem.text;
                Object localObject2 = str;
                if (paramBoolean)
                {
                    localObject1 = arrayOfByte;
                    localObject2 = new StringBuilder();
                    localObject1 = arrayOfByte;
                    ((StringBuilder)localObject2).append(str);
                    localObject1 = arrayOfByte;
                    ((StringBuilder)localObject2).append(String.format(" #%d", new Object[] { Long.valueOf(localDatabaseItem.id) }));
                    localObject1 = arrayOfByte;
                    localObject2 = ((StringBuilder)localObject2).toString();
                }
                localObject1 = arrayOfByte;
                localByteArrayOutputStream.write(Convert.IntToBytes((int)localDatabaseItem.id, true));
                localObject1 = arrayOfByte;
                localByteArrayOutputStream.write(Buffer.EncodeLength((String)localObject2));
                localObject1 = arrayOfByte;
                localByteArrayOutputStream.write(Convert.StringToBytes((String)localObject2));
            }
            localObject1 = arrayOfByte;
            localByteArrayOutputStream.write(Convert.IntToBytes(paramDatabase.unknown1, true));
            localObject1 = arrayOfByte;
            localByteArrayOutputStream.write(Convert.IntToBytes(paramDatabase.unknown2, true));
            localObject1 = arrayOfByte;
            localByteArrayOutputStream.flush();
            localObject1 = arrayOfByte;
            var result = localByteArrayOutputStream.toByteArray();
            //paramDatabase = localByteArrayOutputStream.toByteArray();
            localObject1 = result;
            localByteArrayOutputStream.close();
            return result;
        }
        catch (IOException ex)
        {
        }
        return localObject1;
    }

    public static Checksum ChecksumUnpack(byte[] paramArrayOfByte)
    {
        Checksum localChecksum = new Checksum();
        if (paramArrayOfByte.length == 0)
            return localChecksum;
        Buffer.Payload localPayload = new Buffer.Payload();
        localChecksum.filesize = Buffer.GetLong(paramArrayOfByte, localPayload);
        localChecksum.count = Buffer.GetInt(paramArrayOfByte, localPayload);
        int i = 0;
        while (i < localChecksum.count)
        {
            ChecksumItem localChecksumItem = new ChecksumItem();
            localChecksumItem.name = Buffer.GetString(paramArrayOfByte, localPayload);
            localChecksumItem.filesize = Buffer.GetLong(paramArrayOfByte, localPayload);
            localChecksumItem.hash = Buffer.GetString(paramArrayOfByte, localPayload);
            localChecksum.items.put(localChecksumItem.name, localChecksumItem);
            i += 1;
        }
        return localChecksum;
    }

    public static byte[] ChecksumPack(Checksum paramChecksum)
    {
        byte[] arrayOfByte = new byte[0];
        var localObject = arrayOfByte;
        try
        {
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localObject = arrayOfByte;
            localByteArrayOutputStream.write(Convert.LongToBytes(paramChecksum.filesize, true));
            localObject = arrayOfByte;
            localByteArrayOutputStream.write(Convert.IntToBytes(paramChecksum.items.size(), true));
            localObject = arrayOfByte;
            var listdata = paramChecksum.items.values().iterator();
            //paramChecksum = paramChecksum.items.values().iterator();
            while (true)
            {
                localObject = arrayOfByte;
                if (!listdata.hasNext())
                    break;
                localObject = arrayOfByte;
                ChecksumItem localChecksumItem = (ChecksumItem)listdata.next();
                localObject = arrayOfByte;
                localByteArrayOutputStream.write(Buffer.EncodeLength(localChecksumItem.name));
                localObject = arrayOfByte;
                localByteArrayOutputStream.write(Convert.StringToBytes(localChecksumItem.name));
                localObject = arrayOfByte;
                localByteArrayOutputStream.write(Convert.LongToBytes(localChecksumItem.filesize, true));
                localObject = arrayOfByte;
                localByteArrayOutputStream.write(Buffer.EncodeLength(localChecksumItem.hash));
                localObject = arrayOfByte;
                localByteArrayOutputStream.write(Convert.StringToBytes(localChecksumItem.hash));
            }
            localObject = arrayOfByte;
            localByteArrayOutputStream.flush();
            localObject = arrayOfByte;
            var result = localByteArrayOutputStream.toByteArray();
            //paramChecksum = localByteArrayOutputStream.toByteArray();
            localObject = result;
            localByteArrayOutputStream.close();
            return result;
        }
        catch (IOException ex)
        {
        }
        return localObject;
    }

    public static class Database
    {
        int count;
        LinkedHashMap<Long, DatabaseItem> items = new LinkedHashMap();
        int unknown1;
        int unknown2;
    }

    public static class DatabaseItem
    {
        long id;
        String text;
    }

    public static class Checksum
    {
        int count;
        long filesize;
        LinkedHashMap<String, ChecksumItem> items = new LinkedHashMap();
    }

    public static class ChecksumItem
    {
        long filesize;
        String hash;
        String name;
    }
}

