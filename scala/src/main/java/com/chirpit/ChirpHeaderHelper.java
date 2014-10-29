package com.chirpit;


/**
* com/chirpit/ChirpHeaderHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../idl/chirpit.idl
* Wednesday, October 8, 2014 5:01:55 AM CEST
*/

abstract public class ChirpHeaderHelper
{
  private static String  _id = "IDL:com/chirpit/ChirpHeader:1.0";

  public static void insert (org.omg.CORBA.Any a, com.chirpit.ChirpHeader that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.chirpit.ChirpHeader extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [4];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = com.chirpit.ChirpIdHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          _tcOf_members0 = com.chirpit.LocationHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "location",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ulonglong);
          _members0[2] = new org.omg.CORBA.StructMember (
            "timestamp",
            _tcOf_members0,
            null);
          _tcOf_members0 = com.chirpit.ChirpActionKindHelper.type ();
          _members0[3] = new org.omg.CORBA.StructMember (
            "kind",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (com.chirpit.ChirpHeaderHelper.id (), "ChirpHeader", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.chirpit.ChirpHeader read (org.omg.CORBA.portable.InputStream istream)
  {
    com.chirpit.ChirpHeader value = new com.chirpit.ChirpHeader ();
    value.id = com.chirpit.ChirpIdHelper.read (istream);
    value.location = com.chirpit.LocationHelper.read (istream);
    value.timestamp = istream.read_ulonglong ();
    value.kind = com.chirpit.ChirpActionKindHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.chirpit.ChirpHeader value)
  {
    com.chirpit.ChirpIdHelper.write (ostream, value.id);
    com.chirpit.LocationHelper.write (ostream, value.location);
    ostream.write_ulonglong (value.timestamp);
    com.chirpit.ChirpActionKindHelper.write (ostream, value.kind);
  }

}