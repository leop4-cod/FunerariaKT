package com.funeraria.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.funeraria.app.data.local.TokenDataStore;
import com.funeraria.app.data.remote.api.AuthApi;
import com.funeraria.app.data.remote.api.ClienteApi;
import com.funeraria.app.data.remote.api.ContratoApi;
import com.funeraria.app.data.remote.api.DifuntoApi;
import com.funeraria.app.data.remote.api.EmpleadoApi;
import com.funeraria.app.data.remote.api.PagoApi;
import com.funeraria.app.data.remote.api.ProductoApi;
import com.funeraria.app.data.remote.api.ProveedorApi;
import com.funeraria.app.data.remote.api.ServicioApi;
import com.funeraria.app.data.remote.interceptors.AuthInterceptor;
import com.funeraria.app.data.repository.AuthRepositoryImpl;
import com.funeraria.app.data.repository.ClienteRepositoryImpl;
import com.funeraria.app.data.repository.ContratoRepositoryImpl;
import com.funeraria.app.data.repository.DifuntoRepositoryImpl;
import com.funeraria.app.data.repository.EmpleadoRepositoryImpl;
import com.funeraria.app.data.repository.PagoRepositoryImpl;
import com.funeraria.app.data.repository.ProductoRepositoryImpl;
import com.funeraria.app.data.repository.ProveedorRepositoryImpl;
import com.funeraria.app.data.repository.ServicioRepositoryImpl;
import com.funeraria.app.di.NetworkModule_ProvideAuthApiFactory;
import com.funeraria.app.di.NetworkModule_ProvideClienteApiFactory;
import com.funeraria.app.di.NetworkModule_ProvideContratoApiFactory;
import com.funeraria.app.di.NetworkModule_ProvideDifuntoApiFactory;
import com.funeraria.app.di.NetworkModule_ProvideEmpleadoApiFactory;
import com.funeraria.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.funeraria.app.di.NetworkModule_ProvidePagoApiFactory;
import com.funeraria.app.di.NetworkModule_ProvideProductoApiFactory;
import com.funeraria.app.di.NetworkModule_ProvideProveedorApiFactory;
import com.funeraria.app.di.NetworkModule_ProvideRetrofitFactory;
import com.funeraria.app.di.NetworkModule_ProvideServicioApiFactory;
import com.funeraria.app.ui.viewmodel.AuthViewModel;
import com.funeraria.app.ui.viewmodel.AuthViewModel_HiltModules;
import com.funeraria.app.ui.viewmodel.ClienteViewModel;
import com.funeraria.app.ui.viewmodel.ClienteViewModel_HiltModules;
import com.funeraria.app.ui.viewmodel.ContratoViewModel;
import com.funeraria.app.ui.viewmodel.ContratoViewModel_HiltModules;
import com.funeraria.app.ui.viewmodel.DifuntoViewModel;
import com.funeraria.app.ui.viewmodel.DifuntoViewModel_HiltModules;
import com.funeraria.app.ui.viewmodel.EmpleadoViewModel;
import com.funeraria.app.ui.viewmodel.EmpleadoViewModel_HiltModules;
import com.funeraria.app.ui.viewmodel.PagoViewModel;
import com.funeraria.app.ui.viewmodel.PagoViewModel_HiltModules;
import com.funeraria.app.ui.viewmodel.ProductoViewModel;
import com.funeraria.app.ui.viewmodel.ProductoViewModel_HiltModules;
import com.funeraria.app.ui.viewmodel.ProveedorViewModel;
import com.funeraria.app.ui.viewmodel.ProveedorViewModel_HiltModules;
import com.funeraria.app.ui.viewmodel.ServicioViewModel;
import com.funeraria.app.ui.viewmodel.ServicioViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerFunerariaApplication_HiltComponents_SingletonC {
  private DaggerFunerariaApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public FunerariaApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements FunerariaApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public FunerariaApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements FunerariaApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public FunerariaApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements FunerariaApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public FunerariaApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements FunerariaApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public FunerariaApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements FunerariaApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public FunerariaApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements FunerariaApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public FunerariaApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements FunerariaApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public FunerariaApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends FunerariaApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends FunerariaApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends FunerariaApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends FunerariaApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(9).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_AuthViewModel, AuthViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ClienteViewModel, ClienteViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ContratoViewModel, ContratoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_DifuntoViewModel, DifuntoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_EmpleadoViewModel, EmpleadoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_PagoViewModel, PagoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ProductoViewModel, ProductoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ProveedorViewModel, ProveedorViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ServicioViewModel, ServicioViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_funeraria_app_ui_viewmodel_ContratoViewModel = "com.funeraria.app.ui.viewmodel.ContratoViewModel";

      static String com_funeraria_app_ui_viewmodel_ServicioViewModel = "com.funeraria.app.ui.viewmodel.ServicioViewModel";

      static String com_funeraria_app_ui_viewmodel_ProveedorViewModel = "com.funeraria.app.ui.viewmodel.ProveedorViewModel";

      static String com_funeraria_app_ui_viewmodel_AuthViewModel = "com.funeraria.app.ui.viewmodel.AuthViewModel";

      static String com_funeraria_app_ui_viewmodel_ProductoViewModel = "com.funeraria.app.ui.viewmodel.ProductoViewModel";

      static String com_funeraria_app_ui_viewmodel_DifuntoViewModel = "com.funeraria.app.ui.viewmodel.DifuntoViewModel";

      static String com_funeraria_app_ui_viewmodel_PagoViewModel = "com.funeraria.app.ui.viewmodel.PagoViewModel";

      static String com_funeraria_app_ui_viewmodel_ClienteViewModel = "com.funeraria.app.ui.viewmodel.ClienteViewModel";

      static String com_funeraria_app_ui_viewmodel_EmpleadoViewModel = "com.funeraria.app.ui.viewmodel.EmpleadoViewModel";

      @KeepFieldType
      ContratoViewModel com_funeraria_app_ui_viewmodel_ContratoViewModel2;

      @KeepFieldType
      ServicioViewModel com_funeraria_app_ui_viewmodel_ServicioViewModel2;

      @KeepFieldType
      ProveedorViewModel com_funeraria_app_ui_viewmodel_ProveedorViewModel2;

      @KeepFieldType
      AuthViewModel com_funeraria_app_ui_viewmodel_AuthViewModel2;

      @KeepFieldType
      ProductoViewModel com_funeraria_app_ui_viewmodel_ProductoViewModel2;

      @KeepFieldType
      DifuntoViewModel com_funeraria_app_ui_viewmodel_DifuntoViewModel2;

      @KeepFieldType
      PagoViewModel com_funeraria_app_ui_viewmodel_PagoViewModel2;

      @KeepFieldType
      ClienteViewModel com_funeraria_app_ui_viewmodel_ClienteViewModel2;

      @KeepFieldType
      EmpleadoViewModel com_funeraria_app_ui_viewmodel_EmpleadoViewModel2;
    }
  }

  private static final class ViewModelCImpl extends FunerariaApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<ClienteViewModel> clienteViewModelProvider;

    private Provider<ContratoViewModel> contratoViewModelProvider;

    private Provider<DifuntoViewModel> difuntoViewModelProvider;

    private Provider<EmpleadoViewModel> empleadoViewModelProvider;

    private Provider<PagoViewModel> pagoViewModelProvider;

    private Provider<ProductoViewModel> productoViewModelProvider;

    private Provider<ProveedorViewModel> proveedorViewModelProvider;

    private Provider<ServicioViewModel> servicioViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.clienteViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.contratoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.difuntoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.empleadoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.pagoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.productoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.proveedorViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.servicioViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(9).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_AuthViewModel, ((Provider) authViewModelProvider)).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ClienteViewModel, ((Provider) clienteViewModelProvider)).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ContratoViewModel, ((Provider) contratoViewModelProvider)).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_DifuntoViewModel, ((Provider) difuntoViewModelProvider)).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_EmpleadoViewModel, ((Provider) empleadoViewModelProvider)).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_PagoViewModel, ((Provider) pagoViewModelProvider)).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ProductoViewModel, ((Provider) productoViewModelProvider)).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ProveedorViewModel, ((Provider) proveedorViewModelProvider)).put(LazyClassKeyProvider.com_funeraria_app_ui_viewmodel_ServicioViewModel, ((Provider) servicioViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_funeraria_app_ui_viewmodel_DifuntoViewModel = "com.funeraria.app.ui.viewmodel.DifuntoViewModel";

      static String com_funeraria_app_ui_viewmodel_PagoViewModel = "com.funeraria.app.ui.viewmodel.PagoViewModel";

      static String com_funeraria_app_ui_viewmodel_ProveedorViewModel = "com.funeraria.app.ui.viewmodel.ProveedorViewModel";

      static String com_funeraria_app_ui_viewmodel_ContratoViewModel = "com.funeraria.app.ui.viewmodel.ContratoViewModel";

      static String com_funeraria_app_ui_viewmodel_ServicioViewModel = "com.funeraria.app.ui.viewmodel.ServicioViewModel";

      static String com_funeraria_app_ui_viewmodel_EmpleadoViewModel = "com.funeraria.app.ui.viewmodel.EmpleadoViewModel";

      static String com_funeraria_app_ui_viewmodel_ClienteViewModel = "com.funeraria.app.ui.viewmodel.ClienteViewModel";

      static String com_funeraria_app_ui_viewmodel_ProductoViewModel = "com.funeraria.app.ui.viewmodel.ProductoViewModel";

      static String com_funeraria_app_ui_viewmodel_AuthViewModel = "com.funeraria.app.ui.viewmodel.AuthViewModel";

      @KeepFieldType
      DifuntoViewModel com_funeraria_app_ui_viewmodel_DifuntoViewModel2;

      @KeepFieldType
      PagoViewModel com_funeraria_app_ui_viewmodel_PagoViewModel2;

      @KeepFieldType
      ProveedorViewModel com_funeraria_app_ui_viewmodel_ProveedorViewModel2;

      @KeepFieldType
      ContratoViewModel com_funeraria_app_ui_viewmodel_ContratoViewModel2;

      @KeepFieldType
      ServicioViewModel com_funeraria_app_ui_viewmodel_ServicioViewModel2;

      @KeepFieldType
      EmpleadoViewModel com_funeraria_app_ui_viewmodel_EmpleadoViewModel2;

      @KeepFieldType
      ClienteViewModel com_funeraria_app_ui_viewmodel_ClienteViewModel2;

      @KeepFieldType
      ProductoViewModel com_funeraria_app_ui_viewmodel_ProductoViewModel2;

      @KeepFieldType
      AuthViewModel com_funeraria_app_ui_viewmodel_AuthViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.funeraria.app.ui.viewmodel.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.authRepositoryImplProvider.get(), singletonCImpl.tokenDataStoreProvider.get());

          case 1: // com.funeraria.app.ui.viewmodel.ClienteViewModel 
          return (T) new ClienteViewModel(singletonCImpl.clienteRepositoryImplProvider.get());

          case 2: // com.funeraria.app.ui.viewmodel.ContratoViewModel 
          return (T) new ContratoViewModel(singletonCImpl.contratoRepositoryImplProvider.get());

          case 3: // com.funeraria.app.ui.viewmodel.DifuntoViewModel 
          return (T) new DifuntoViewModel(singletonCImpl.difuntoRepositoryImplProvider.get());

          case 4: // com.funeraria.app.ui.viewmodel.EmpleadoViewModel 
          return (T) new EmpleadoViewModel(singletonCImpl.empleadoRepositoryImplProvider.get());

          case 5: // com.funeraria.app.ui.viewmodel.PagoViewModel 
          return (T) new PagoViewModel(singletonCImpl.pagoRepositoryImplProvider.get());

          case 6: // com.funeraria.app.ui.viewmodel.ProductoViewModel 
          return (T) new ProductoViewModel(singletonCImpl.productoRepositoryImplProvider.get());

          case 7: // com.funeraria.app.ui.viewmodel.ProveedorViewModel 
          return (T) new ProveedorViewModel(singletonCImpl.proveedorRepositoryImplProvider.get());

          case 8: // com.funeraria.app.ui.viewmodel.ServicioViewModel 
          return (T) new ServicioViewModel(singletonCImpl.servicioRepositoryImplProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends FunerariaApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends FunerariaApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends FunerariaApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<TokenDataStore> tokenDataStoreProvider;

    private Provider<AuthInterceptor> authInterceptorProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<AuthApi> provideAuthApiProvider;

    private Provider<AuthRepositoryImpl> authRepositoryImplProvider;

    private Provider<ClienteApi> provideClienteApiProvider;

    private Provider<ClienteRepositoryImpl> clienteRepositoryImplProvider;

    private Provider<ContratoApi> provideContratoApiProvider;

    private Provider<ContratoRepositoryImpl> contratoRepositoryImplProvider;

    private Provider<DifuntoApi> provideDifuntoApiProvider;

    private Provider<DifuntoRepositoryImpl> difuntoRepositoryImplProvider;

    private Provider<EmpleadoApi> provideEmpleadoApiProvider;

    private Provider<EmpleadoRepositoryImpl> empleadoRepositoryImplProvider;

    private Provider<PagoApi> providePagoApiProvider;

    private Provider<PagoRepositoryImpl> pagoRepositoryImplProvider;

    private Provider<ProductoApi> provideProductoApiProvider;

    private Provider<ProductoRepositoryImpl> productoRepositoryImplProvider;

    private Provider<ProveedorApi> provideProveedorApiProvider;

    private Provider<ProveedorRepositoryImpl> proveedorRepositoryImplProvider;

    private Provider<ServicioApi> provideServicioApiProvider;

    private Provider<ServicioRepositoryImpl> servicioRepositoryImplProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.tokenDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<TokenDataStore>(singletonCImpl, 5));
      this.authInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<AuthInterceptor>(singletonCImpl, 4));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 3));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 2));
      this.provideAuthApiProvider = DoubleCheck.provider(new SwitchingProvider<AuthApi>(singletonCImpl, 1));
      this.authRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepositoryImpl>(singletonCImpl, 0));
      this.provideClienteApiProvider = DoubleCheck.provider(new SwitchingProvider<ClienteApi>(singletonCImpl, 7));
      this.clienteRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ClienteRepositoryImpl>(singletonCImpl, 6));
      this.provideContratoApiProvider = DoubleCheck.provider(new SwitchingProvider<ContratoApi>(singletonCImpl, 9));
      this.contratoRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ContratoRepositoryImpl>(singletonCImpl, 8));
      this.provideDifuntoApiProvider = DoubleCheck.provider(new SwitchingProvider<DifuntoApi>(singletonCImpl, 11));
      this.difuntoRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<DifuntoRepositoryImpl>(singletonCImpl, 10));
      this.provideEmpleadoApiProvider = DoubleCheck.provider(new SwitchingProvider<EmpleadoApi>(singletonCImpl, 13));
      this.empleadoRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<EmpleadoRepositoryImpl>(singletonCImpl, 12));
      this.providePagoApiProvider = DoubleCheck.provider(new SwitchingProvider<PagoApi>(singletonCImpl, 15));
      this.pagoRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<PagoRepositoryImpl>(singletonCImpl, 14));
      this.provideProductoApiProvider = DoubleCheck.provider(new SwitchingProvider<ProductoApi>(singletonCImpl, 17));
      this.productoRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ProductoRepositoryImpl>(singletonCImpl, 16));
      this.provideProveedorApiProvider = DoubleCheck.provider(new SwitchingProvider<ProveedorApi>(singletonCImpl, 19));
      this.proveedorRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ProveedorRepositoryImpl>(singletonCImpl, 18));
      this.provideServicioApiProvider = DoubleCheck.provider(new SwitchingProvider<ServicioApi>(singletonCImpl, 21));
      this.servicioRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ServicioRepositoryImpl>(singletonCImpl, 20));
    }

    @Override
    public void injectFunerariaApplication(FunerariaApplication arg0) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.funeraria.app.data.repository.AuthRepositoryImpl 
          return (T) new AuthRepositoryImpl(singletonCImpl.provideAuthApiProvider.get(), singletonCImpl.tokenDataStoreProvider.get());

          case 1: // com.funeraria.app.data.remote.api.AuthApi 
          return (T) NetworkModule_ProvideAuthApiFactory.provideAuthApi(singletonCImpl.provideRetrofitProvider.get());

          case 2: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 3: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.authInterceptorProvider.get());

          case 4: // com.funeraria.app.data.remote.interceptors.AuthInterceptor 
          return (T) new AuthInterceptor(singletonCImpl.tokenDataStoreProvider.get());

          case 5: // com.funeraria.app.data.local.TokenDataStore 
          return (T) new TokenDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // com.funeraria.app.data.repository.ClienteRepositoryImpl 
          return (T) new ClienteRepositoryImpl(singletonCImpl.provideClienteApiProvider.get());

          case 7: // com.funeraria.app.data.remote.api.ClienteApi 
          return (T) NetworkModule_ProvideClienteApiFactory.provideClienteApi(singletonCImpl.provideRetrofitProvider.get());

          case 8: // com.funeraria.app.data.repository.ContratoRepositoryImpl 
          return (T) new ContratoRepositoryImpl(singletonCImpl.provideContratoApiProvider.get());

          case 9: // com.funeraria.app.data.remote.api.ContratoApi 
          return (T) NetworkModule_ProvideContratoApiFactory.provideContratoApi(singletonCImpl.provideRetrofitProvider.get());

          case 10: // com.funeraria.app.data.repository.DifuntoRepositoryImpl 
          return (T) new DifuntoRepositoryImpl(singletonCImpl.provideDifuntoApiProvider.get());

          case 11: // com.funeraria.app.data.remote.api.DifuntoApi 
          return (T) NetworkModule_ProvideDifuntoApiFactory.provideDifuntoApi(singletonCImpl.provideRetrofitProvider.get());

          case 12: // com.funeraria.app.data.repository.EmpleadoRepositoryImpl 
          return (T) new EmpleadoRepositoryImpl(singletonCImpl.provideEmpleadoApiProvider.get());

          case 13: // com.funeraria.app.data.remote.api.EmpleadoApi 
          return (T) NetworkModule_ProvideEmpleadoApiFactory.provideEmpleadoApi(singletonCImpl.provideRetrofitProvider.get());

          case 14: // com.funeraria.app.data.repository.PagoRepositoryImpl 
          return (T) new PagoRepositoryImpl(singletonCImpl.providePagoApiProvider.get());

          case 15: // com.funeraria.app.data.remote.api.PagoApi 
          return (T) NetworkModule_ProvidePagoApiFactory.providePagoApi(singletonCImpl.provideRetrofitProvider.get());

          case 16: // com.funeraria.app.data.repository.ProductoRepositoryImpl 
          return (T) new ProductoRepositoryImpl(singletonCImpl.provideProductoApiProvider.get());

          case 17: // com.funeraria.app.data.remote.api.ProductoApi 
          return (T) NetworkModule_ProvideProductoApiFactory.provideProductoApi(singletonCImpl.provideRetrofitProvider.get());

          case 18: // com.funeraria.app.data.repository.ProveedorRepositoryImpl 
          return (T) new ProveedorRepositoryImpl(singletonCImpl.provideProveedorApiProvider.get());

          case 19: // com.funeraria.app.data.remote.api.ProveedorApi 
          return (T) NetworkModule_ProvideProveedorApiFactory.provideProveedorApi(singletonCImpl.provideRetrofitProvider.get());

          case 20: // com.funeraria.app.data.repository.ServicioRepositoryImpl 
          return (T) new ServicioRepositoryImpl(singletonCImpl.provideServicioApiProvider.get());

          case 21: // com.funeraria.app.data.remote.api.ServicioApi 
          return (T) NetworkModule_ProvideServicioApiFactory.provideServicioApi(singletonCImpl.provideRetrofitProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
