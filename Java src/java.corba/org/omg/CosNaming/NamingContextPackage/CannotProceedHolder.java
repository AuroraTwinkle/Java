package org.omg.CosNaming.NamingContextPackage;

/**
* org/omg/CosNaming/NamingContextPackage/CannotProceedHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from t:/workspace/open/src/java.corba/share/classes/org/omg/CosNaming/nameservice.idl
* Monday, March 26, 2018 at 7:22:27 PM Mountain Daylight Time
*/

public final class CannotProceedHolder implements org.omg.CORBA.portable.Streamable
{
  public org.omg.CosNaming.NamingContextPackage.CannotProceed value = null;

  public CannotProceedHolder ()
  {
  }

  public CannotProceedHolder (org.omg.CosNaming.NamingContextPackage.CannotProceed initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.type ();
  }

}
