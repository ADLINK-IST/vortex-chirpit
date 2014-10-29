package com.chirpit;


/**
* com/chirpit/UserStats.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../idl/chirpit.idl
* Wednesday, October 8, 2014 5:01:55 AM CEST
*/

/**
* Updated by idl2j
* from ../idl/chirpit.idl
* Wednesday, October 8, 2014 5:01:56 AM CEST
*/

import com.prismtech.cafe.dcps.keys.KeyList;

@KeyList(
    topicType = "UserStats",
    keys = {"userId"}
)
public final class UserStats implements org.omg.CORBA.portable.IDLEntity
{
  public String userId = null;
  public int followers = (int)0;
  public int chirps = (int)0;
  public int followed = (int)0;

  public UserStats ()
  {
  } // ctor

  public UserStats (String _userId, int _followers, int _chirps, int _followed)
  {
    userId = _userId;
    followers = _followers;
    chirps = _chirps;
    followed = _followed;
  } // ctor

} // class UserStats