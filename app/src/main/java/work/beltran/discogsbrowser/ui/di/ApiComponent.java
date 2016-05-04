package work.beltran.discogsbrowser.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.ui.collection.CollectionFragment;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterModule;
import work.beltran.discogsbrowser.ui.di.modules.WantRecordsAdapterModule;
import work.beltran.discogsbrowser.ui.wantlist.WantlistFragment;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
@Singleton
@Component(modules = {RecordsAdapterModule.class, WantRecordsAdapterModule.class})
public interface ApiComponent {
    void inject(CollectionFragment fragment);
    void inject(WantlistFragment fragment);
    ApiFrontend userCollection();
}
