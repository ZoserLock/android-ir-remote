# Android IR Remote Control Application
A simple Android App showing the implementation of the Consumer IR Manager in Android to use the IR Blaster in the phone to control IR Devices.

I use this to control all the IR devices in my living room.

## Features
* Functions to create SIRC Protocol messages.
    * 12, 15 and 20 bit variations.
* Functions to create NEC Protocol messages.

# About the code.

## To Create a SIRC Protocol Message
```cs
// IRSonyFactory.Create(type,command, address, repeats)
IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,84,10,4);
```

## To Create a NEC Protocol Message
```cs
// IRNecFactory.create(command,address,repeats);
IRNecFactory.create(16,32,3);;
```

## To Send a message using the IR
```cs
  IRMessage msg = IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,84,10,4);
  IRController irController = new IRController(getApplicationContext());
  irController.sendMessage(msg);
```

# Screenshots
![Example](https://raw.githubusercontent.com/ZoserLock/android-ir-remote/master/images/image.png)

