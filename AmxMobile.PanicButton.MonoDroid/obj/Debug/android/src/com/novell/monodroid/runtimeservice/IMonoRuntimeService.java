/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: src/com/novell/monodroid/runtimeservice/IMonoRuntimeService.aidl
 */
package com.novell.monodroid.runtimeservice;
public interface IMonoRuntimeService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.novell.monodroid.runtimeservice.IMonoRuntimeService
{
private static final java.lang.String DESCRIPTOR = "com.novell.monodroid.runtimeservice.IMonoRuntimeService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.novell.monodroid.runtimeservice.IMonoRuntimeService interface,
 * generating a proxy if needed.
 */
public static com.novell.monodroid.runtimeservice.IMonoRuntimeService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.novell.monodroid.runtimeservice.IMonoRuntimeService))) {
return ((com.novell.monodroid.runtimeservice.IMonoRuntimeService)iin);
}
return new com.novell.monodroid.runtimeservice.IMonoRuntimeService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getRuntimePath:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getRuntimePath();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getRuntimeVersion:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getRuntimeVersion();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.novell.monodroid.runtimeservice.IMonoRuntimeService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public java.lang.String getRuntimePath() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRuntimePath, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public int getRuntimeVersion() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRuntimeVersion, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getRuntimePath = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getRuntimeVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public java.lang.String getRuntimePath() throws android.os.RemoteException;
public int getRuntimeVersion() throws android.os.RemoteException;
}
