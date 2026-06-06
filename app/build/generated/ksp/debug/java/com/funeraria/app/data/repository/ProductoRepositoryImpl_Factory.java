package com.funeraria.app.data.repository;

import com.funeraria.app.data.remote.api.ProductoApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ProductoRepositoryImpl_Factory implements Factory<ProductoRepositoryImpl> {
  private final Provider<ProductoApi> apiProvider;

  public ProductoRepositoryImpl_Factory(Provider<ProductoApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ProductoRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ProductoRepositoryImpl_Factory create(Provider<ProductoApi> apiProvider) {
    return new ProductoRepositoryImpl_Factory(apiProvider);
  }

  public static ProductoRepositoryImpl newInstance(ProductoApi api) {
    return new ProductoRepositoryImpl(api);
  }
}
