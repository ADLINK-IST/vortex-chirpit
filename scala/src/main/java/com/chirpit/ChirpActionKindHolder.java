package com.chirpit;

/**
* com/chirpit/ChirpActionKindHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../idl/chirpit.idl
* Wednesday, October 8, 2014 5:01:55 AM CEST
*/

public final class ChirpActionKindHolder implements org.omg.CORBA.portable.Streamable
{
  public com.chirpit.ChirpActionKind value = null;

  public ChirpActionKindHolder ()
  {
  }

  public ChirpActionKindHolder (com.chirpit.ChirpActionKind initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.chirpit.ChirpActionKindHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.chirpit.ChirpActionKindHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.chirpit.ChirpActionKindHelper.type ();
  }

}