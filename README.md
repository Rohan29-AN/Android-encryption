# Android Encryptation

This is a package creation test. It contains the encryption method with AES and RSA in java. Currently, this project is only a beta version. It is possible that it still has some bugs😁😅 .

> Step 1. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```
  
 > Step 2. Add the dependency
 
```gradle
 dependencies {
	         implementation 'com.github.Rohan29-AN:Android-encryption:0.9'
	}
````

# Usage - RSA

> **Encryption:** For encryption, you need to call the method **EncryptOrDecryptRSA**

```java
	String data=EncryptionUtilities.EncryptOrDecryptRSA(true,"test",_keyPublic);
	
```

> **Decryption:**For decryption, this is the same as encryption, but the only difference is that the **isEncryption** variable must be *false*.

```java
	String data=EncryptionUtilities.EncryptOrDecryptRSA(false,"ree45d8d8fd8fd5f4df5d4f",_keyPrivate);
	
```

# Usage - AES
> **Encryption/Decryption:** In the AES, you need to call the method **EncryptionOrDecryptAES**

```java
	//This function requires 4 parameter
	/**
	     * @param isEncryption true if the key is for encryption, false if it is for decryption
	     * @param value word to encrypt or decrypt
	     * @param key the key string (This key is the encryption key and the decryption key)
	     * @return resultat This method returns a string if decryption or encryption goes well and null if a problem occurs
	*/
	String dataAES=EncryptionUtilities.EncryptionOrDecryptAES(Boolean isEncryption,String value,String key,String iv)
	
```

# Contributing
	* Fork it
	* Create your feature branch (git checkout -b my-new-feature)
	* Commit your changes (git commit -am 'Added some feature')
	* Push to the branch (git push origin my-new-feature)
	* Create new Pull Request
	
#T O-DO LISTS
	* Test
	* Refactoring the code to have the stable version 1.0 😁
	* Addition of new encryption algorithms
	* .....

# Licence
Copyright (c) 2023 Rohan29-AN


