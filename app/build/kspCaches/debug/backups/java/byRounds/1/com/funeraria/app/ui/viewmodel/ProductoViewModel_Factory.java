package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.domain.repository.ProductoRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ProductoViewModel_Factory implements Factory<ProductoViewModel> {
  private final Provider<ProductoRepository> repoProvider;

  public ProductoViewModel_Factory(Provider<ProductoRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ProductoViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static ProductoViewModel_Factory create(Provider<ProductoRepository> repoProvider) {
    return new ProductoViewModel_Factory(repoProvider);
  }

  public static ProductoViewModel newInstance(ProductoRepository repo) {
    return new ProductoViewModel(repo);
  }
}
