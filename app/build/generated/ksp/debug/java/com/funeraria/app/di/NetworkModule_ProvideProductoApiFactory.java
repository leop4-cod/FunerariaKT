package com.funeraria.app.di;

import com.funeraria.app.data.remote.api.ProductoApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class NetworkModule_ProvideProductoApiFactory implements Factory<ProductoApi> {
  private final Provider<Retrofit> rProvider;

  public NetworkModule_ProvideProductoApiFactory(Provider<Retrofit> rProvider) {
    this.rProvider = rProvider;
  }

  @Override
  public ProductoApi get() {
    return provideProductoApi(rProvider.get());
  }

  public static NetworkModule_ProvideProductoApiFactory create(Provider<Retrofit> rProvider) {
    return new NetworkModule_ProvideProductoApiFactory(rProvider);
  }

  public static ProductoApi provideProductoApi(Retrofit r) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideProductoApi(r));
  }
}
