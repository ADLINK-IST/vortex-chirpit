package com.chirpit;


/**
* com/chirpit/SomeLocationHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../idl/chirpit.idl
* Wednesday, October 8, 2014 5:01:55 AM CEST
*/

abstract public class SomeLocationHelper
{
  private static String  _id = "IDL:com/chirpit/SomeLocation:1.0";

  public static void insert (org.omg.CORBA.Any a, com.chirpit.SomeLocation that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.chirpit.SomeLocation extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[0] = new org.omg.CORBA.StructMember (
            "longitude",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[1] = new org.omg.CORBA.StructMember (
            "latitude",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (com.chirpit.SomeLocationHelper.id (), "SomeLocation", _members0);
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

  public static com.chirpit.SomeLocation read (org.omg.CORBA.portable.InputStream istream)
  {
    com.chirpit.SomeLocation value = new com.chirpit.SomeLocation ();
    value.longitude = istream.read_float ();
    value.latitude = istream.read_float ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.chirpit.SomeLocation value)
  {
    ostream.write_float (value.longitude);
    ostream.write_float (value.latitude);
  }

}