import java.nio.charset.StandardCharsets;

public class Convert {
    public static final String BytesToHex(byte[] paramArrayOfByte)
    {
        int j = paramArrayOfByte.length;
        char[] arrayOfChar = new char[j * 2];
        int i = 0;
        while (i < j)
        {
            int k = paramArrayOfByte[i] & 0xFF;
            int m = i * 2;
            arrayOfChar[m] = Character.forDigit(k >>> 4, 16);
            arrayOfChar[(m + 1)] = Character.forDigit(k & 0xF, 16);
            i += 1;
        }
        return new String(arrayOfChar);
    }

    public static int BytesToInt(byte[] paramArrayOfByte)
    {
        return BytesToInt(paramArrayOfByte, false);
    }

    public static int BytesToInt(byte[] paramArrayOfByte, boolean paramBoolean)
    {
        int i = 0;
        if (paramArrayOfByte != null)
        {
            if (paramArrayOfByte.length != 4)
                return 0;
            int j = 0;
            while (i < 4)
            {
                int k;
                if (paramBoolean)
                    k = 4 - i - 1;
                else
                    k = i;
                j = j << 8 | paramArrayOfByte[k] & 0xFF;
                i += 1;
            }
            return j;
        }
        return 0;
    }

    public static long BytesToLong(byte[] paramArrayOfByte)
    {
        return BytesToLong(paramArrayOfByte, false);
    }

    public static long BytesToLong(byte[] paramArrayOfByte, boolean paramBoolean)
    {
        if ((paramArrayOfByte != null) && (paramArrayOfByte.length == 8))
        {
            int i = 0;
            int j = 0;
            while (i < 8)
            {
                int k;
                if (paramBoolean)
                    k = 8 - i - 1;
                else
                    k = i;
                j = j << 8 | paramArrayOfByte[k] & 0xFF;
                i += 1;
            }
            return j;
        }
        return 0L;
    }

    public static String BytesToString(byte[] paramArrayOfByte)
    {
        return new String(paramArrayOfByte, StandardCharsets.UTF_8);
    }

    public static final byte[] HexToBytes(String paramString)
    {
        int j = paramString.length();
        byte[] arrayOfByte = new byte[j / 2];
        int i = 0;
        while (i < j)
        {
            arrayOfByte[(i / 2)] = ((byte)((Character.digit(paramString.charAt(i), 16) << 4) + Character.digit(paramString.charAt(i + 1), 16)));
            i += 2;
        }
        return arrayOfByte;
    }

    public static byte[] IntToBytes(int paramInt)
    {
        return IntToBytes(paramInt, false);
    }

    public static byte[] IntToBytes(int paramInt, boolean paramBoolean)
    {
        byte[] arrayOfByte = new byte[4];
        int j = 0;
        int i = paramInt;
        paramInt = j;
        while (paramInt < 4)
        {
            if (paramBoolean)
                j = paramInt;
            else
                j = 4 - paramInt - 1;
            arrayOfByte[j] = ((byte)(i & 0xFF));
            i >>= 8;
            paramInt += 1;
        }
        return arrayOfByte;
    }

    public static byte[] LongToBytes(long paramLong)
    {
        return LongToBytes(paramLong, false);
    }

    public static byte[] LongToBytes(long paramLong, boolean paramBoolean)
    {
        byte[] arrayOfByte = new byte[8];
        int i = 0;
        while (i < 8)
        {
            int j;
            if (paramBoolean)
                j = i;
            else
                j = 8 - i - 1;
            arrayOfByte[j] = ((byte)(int)(0xFF & paramLong));
            paramLong >>= 8;
            i += 1;
        }
        return arrayOfByte;
    }

//    public static byte[] ReverseBytes(byte[] paramArrayOfByte)
//    {
//        int k = paramArrayOfByte.length;
//        int j = 0;
//        while (j < k / 2)
//        {
//            int i = paramArrayOfByte[j];
//            int m = k - j - 1;
//            paramArrayOfByte[j] = paramArrayOfByte[m];
//            paramArrayOfByte[m] = i;
//            j += 1;
//        }
//        return paramArrayOfByte;
//    }

    public static byte[] StringToBytes(String paramString)
    {
        return paramString.getBytes(StandardCharsets.UTF_8);
    }

    public static long Unsigned(byte paramByte)
    {
        return paramByte & 0xFF;
    }

    public static long Unsigned(int paramInt)
    {
        return paramInt & 0xFFFFFFFF;
    }

    public static long Unsigned(short paramShort)
    {
        return paramShort & 0xFFFF;
    }
}
