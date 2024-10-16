package com.example.adressbook

import android.os.Parcel
import android.os.Parcelable

class Person(var name: String?, var secondName: String?, var address: String?, var phoneNumber: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(secondName)
        dest.writeString(address)
        dest.writeString(phoneNumber)
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }
        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }

    }

    override fun toString(): String {
        return "$name $secondName"
    }
}
