package com.simplesockettester.utils;

public class Util {
	public static String SHARED_PREFERENCES_NAME = "Preferences";
	
	
    //http://javarevisited.blogspot.sg/2013/03/convert-and-print-byte-array-to-hex-string-java-example-tutorial.html
    public static byte[] fromHexStringToByteArray(String s) throws Exception{
        s = s.trim();
        if( (s.length() % 2) != 0) throw new Exception(); 
        if( s.isEmpty() ) return new byte[0];
        byte[] array = new byte[s.length()/2];
        for(int i=0, j=0; j<s.length(); i++, j=j+2){
            array[i] = (byte)Integer.parseInt(s.substring(j, j+2), 16);
        }
        return array;
    }
    
    public static String fromByteArrayToHexString(byte[] array){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<array.length; i++){
            sb.append( String.format("%02X", array[i]) );
        }
        return sb.toString().toUpperCase();
    }
    
    /*
    public static void changeInfoToBytesStringOrToString(EditText editText, boolean toHex){
    	String infoEditText = editText.getText().toString().trim();
    	
    	if( !infoEditText.equals("") ){
    		try{
	    		if(toHex){
	    			editText.setText( fromByteArrayToHexString( infoEditText.getBytes(CHARSET) ) );
	    		}
	    		else{
	    			editText.setText( new String(fromHexStringToByteArray( infoEditText ), CHARSET) );
	    		}
    		}catch(Exception e){
    			editText.setText("");
    		}
    	}
    }
    */
    
    public static void main(String[] args) throws Exception{
        
        byte[] res = fromHexStringToByteArray("0b0ccc093301");
        for(int i=0; i<res.length; i++){
            System.out.print("  "+res[i]);
        }
        System.out.println();
        
        System.out.print(fromByteArrayToHexString(res));
        
        
        System.out.println();
        System.out.print("------------------------------");
        System.out.println();
        
        String s = "hol�aa";
        byte[] array = s.getBytes("UTF-8");
        for(int i=0; i<array.length; i++){
            System.out.print("  "+array[i]);
        }
        System.out.println();
        System.out.print(new String(array,"UTF-8"));
        //iso-8859-1
        
        System.out.println();
    }
    
}
