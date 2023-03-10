package com.odc.espacediscussion.di

import android.content.Context
import com.odc.espacediscussion.api.EspaceAPI
import com.odc.espacediscussion.api.UserAPI
import com.odc.espacediscussion.depot.EspaceDepot
import com.odc.espacediscussion.depot.UserDepot
import com.odc.espacediscussion.utils.Constantes
import com.odc.espacediscussion.utils.SocketUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAnnot

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EspaceAPIAnnot

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserAPIAnnot

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SocketUtilsAnnot

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(
        @ApplicationContext context: Context,
    ): Context {
        return context
    }

    @Singleton
    @Provides
    @RetrofitAnnot
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }

    /*
   retrofit API
     */
    @Singleton
    @Provides
    @EspaceAPIAnnot
    fun provideEspaceAPi(@RetrofitAnnot retrofit: Retrofit): EspaceAPI {
        return retrofit.create(EspaceAPI::class.java)
    }

    @Singleton
    @Provides
    @UserAPIAnnot
    fun provideUserAPi(@RetrofitAnnot retrofit: Retrofit): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }

    /*
    DEPOTS
     */
    @Singleton
    @Provides
    fun provideEspaceDepot(@EspaceAPIAnnot api: EspaceAPI): EspaceDepot {
        return EspaceDepot(api)
    }

    @Singleton
    @Provides
    fun provideUserDepot(@UserAPIAnnot api: UserAPI): UserDepot {
        return UserDepot(api)
    }

    /*
    SOCKET
     */
    @Singleton
    @Provides
    @SocketUtilsAnnot
    fun provideSocket(@ApplicationContext appContext: Context): SocketUtils {
        return SocketUtils(appContext)
    }


}