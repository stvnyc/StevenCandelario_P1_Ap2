package edu.ucne.stevencandelario_p1_ap2.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.stevencandelario_p1_ap2.data.local.database.PrimerParcialDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule{
    @Provides
    @Singleton
    fun providePrimerParcialDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            PrimerParcialDb::class.java,
            "PrimerParcialDb"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideAlgoDao(primerParcialDb: PrimerParcialDb) = primerParcialDb.ventaDao()
}