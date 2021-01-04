import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class Buffer {
//    public static byte[] EncodeLength(String paramString)
//    {
//        byte[] arrayOfByte = new byte[0];
//        Object localObject = arrayOfByte;
//        while (true)
//        {
//            int i;
//            try
//            {
//                ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
//                localObject = arrayOfByte;
//                i = Convert.StringToBytes(paramString).length;
//                if ((i >= 0) && (i < 128))
//                {
//                    localObject = arrayOfByte;
//                    localByteArrayOutputStream.write(i);
//                    continue;
//                    if (i > 0)
//                    {
//                        localObject = arrayOfByte;
//                        localByteArrayOutputStream.write(i & 0x7F | 0x80);
//                        i >>= 7;
//                        continue;
//                    }
//                    localObject = arrayOfByte;
//                    localByteArrayOutputStream.flush();
//                    localObject = arrayOfByte;
//                    paramString = localByteArrayOutputStream.toByteArray();
//                    localObject = paramString;
//                    i = paramString.length - 1;
//                    paramString[i] = ((byte)(paramString[i] & 0x7F));
//                    return paramString;
//                }
//            }
//            catch (Exception ex)
//            {
//                ex.printStackTrace();
//                return localObject;
//            }
//            if (i <= 0);
//        }
//    }

    /*
    byte[] b = string.getBytes(StandardCharsets.UTF_8);
    * */

//    public static byte[] EncodeLength(String paramString)
//    {
//        byte[] b = paramString.getBytes(StandardCharsets.UTF_16);
//
//        return b;
//    }

    public static byte[] EncodeLength(String paramString)
    {
        byte[] arrayOfByte = new byte[0];
        Object localObject = arrayOfByte;
        while (true)
        {
            int i;
            try
            {
                ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
                localObject = arrayOfByte;
                i = Convert.StringToBytes(paramString).length;
                //if ((i >= 0) && (i < 128))
                //{
                    localObject = arrayOfByte;
                    localByteArrayOutputStream.write(i);
                    //continue;
                    if (i > 0)
                    {
                        localObject = arrayOfByte;
                        localByteArrayOutputStream.write(i & 0x7F | 0x80);
                        i = i >> 7;
                        //continue;
                    }
                    localObject = arrayOfByte;
                    localByteArrayOutputStream.flush();
                    localObject = arrayOfByte;
                    byte[] bArray = localByteArrayOutputStream.toByteArray();

                    localObject = bArray;
                    i = bArray.length - 1;
                    bArray[i] = ((byte)(bArray[i] & 0x7F));
                    return bArray;
                //}
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return (byte[]) localObject;
            }
            //if (i <= 0);
        }
    }

    public static int GetInt(byte[] paramArrayOfByte, Payload paramPayload)
    {
        ReadData(4, paramArrayOfByte, paramPayload);
        return Convert.BytesToInt(paramPayload.bytes, true);
    }

    public static long GetLong(byte[] paramArrayOfByte, Payload paramPayload)
    {
        ReadData(8, paramArrayOfByte, paramPayload);
        return Convert.BytesToLong(paramPayload.bytes, true);
    }

    public static String GetString(byte[] paramArrayOfByte, Payload paramPayload)
    {
        ReadData(ReadLength(paramArrayOfByte, paramPayload), paramArrayOfByte, paramPayload);
        return Convert.BytesToString(paramPayload.bytes);
    }

    private static void ReadData(int paramInt, byte[] paramArrayOfByte, Payload paramPayload)
    {
        paramPayload.bytes = new byte[0];
        if (paramPayload.pos + paramInt <= paramArrayOfByte.length)
        {
            paramPayload.bytes = new byte[paramInt];
            System.arraycopy(paramArrayOfByte, paramPayload.pos, paramPayload.bytes, 0, paramInt);
        }
        paramPayload.pos += paramInt;
    }

    public static int ReadLength(byte[] paramArrayOfByte, Payload paramPayload)
    {
        int i = 0;
        int j = 0;
        int k;
        long l;
        do
        {
            if (paramPayload.pos >= paramArrayOfByte.length)
                return i;
            k = paramArrayOfByte[paramPayload.pos];
            l = Convert.Unsigned(paramArrayOfByte[paramPayload.pos]);
            k = (int)(i | (l & 0x7F) << j);
            j += 7;
            paramPayload.pos += 1;
            i = k;
        }
        while (l > 127L);
        return k;
    }

    public static class Payload
    {
        byte[] bytes;
        int pos = 0;
    }
}
