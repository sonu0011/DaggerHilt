package com.example.daggerhiltexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @Inject
    lateinit var someClass: SomeClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ${someClass.doSomething()}")
    }
}


@ActivityScoped
class SomeClass @Inject constructor(
    private val someOtherClass1: SomeOtherClass1,
    @Impl1 private val abc1: abc,
    @Impl2 private val abc2: abc
) {
    fun doSomething(): String {
        return "Do some work ${abc1.doSomeWokrk()} ${abc2.doSomeWokrk()}  "

    }
}

class SomeOtherClass1 @Inject constructor() : abc {
    fun doSomething(): String {
        return "\nDo some  other work"
    }

    override fun doSomeWokrk(): String {
        return "SomeOtherClass1 work done"
    }
}

class SomeOtherClass2 @Inject constructor() : abc {
    fun doSomething(): String {
        return "\nDo some  other work"
    }
    override fun doSomeWokrk(): String {
        return "SomeOtherClass2 work done"
    }
}

interface abc {
    fun doSomeWokrk(): String
}

//@InstallIn(ApplicationComponent::class)
//@Module
//abstract class MyModule{
//    @Singleton
//    @Binds
//  abstract fun bindAbcImplementation(
//        someOtherClass1: SomeOtherClass1
//    ) : abc
//}

@InstallIn(ApplicationComponent::class)
@Module
class MyModule {

    @Impl1
    @Singleton
    @Provides
    fun bindAbcImplementation1(): abc {
        return SomeOtherClass1();
    }

    @Impl2
    @Singleton
    @Provides
    fun bindAbcImplementation2(): abc {
        return SomeOtherClass2();
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2



