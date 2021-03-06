package work.beltran.discogsbrowser.app.search;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.app.base.BasePresenterForAdapter;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;
import work.beltran.discogsbrowser.business.SearchInteractor;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class SearchPresenter extends BasePresenterForAdapter<SearchView> {

    SearchInteractor interactor;
    private String barcode;

    public SearchPresenter(SearchInteractor interactor) {
        this.interactor = interactor;
    }

    public void search(String query) {
        if (getView() != null) {
            getView().setLoading(true);
            getView().clear();
            unsubscribeAll();
        }
        addSubscription(
                interactor
                        .search(query)
                        .subscribe(new Observer<List<Record>>() {
                            @Override
                            public void onCompleted() {
                                if (getView() != null)
                                    getView().setLoading(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                if (getView() != null)
                                    getView().setLoading(false);
                            }

                            @Override
                            public void onNext(List<Record> records) {
                                if (getView() != null) {
                                    getView().addRecords(RecordAdapterItem.createRecordsList(records, false));
                                }
                            }
                        }));
    }

    @Override
    public Bundle getStatus() {
        Bundle bundle = new Bundle();
        bundle.putString("BARCODE", barcode);
        return bundle;
    }

    @Override
    public void loadStatus(Bundle bundle) {
        String barcode = bundle.getString("BARCODE");
        if (!TextUtils.isEmpty(barcode)) {
            search(barcode);
        }
    }

    @Override
    public void loadMore() {

    }

    public void searchWithBarcode(String contents) {
        search(contents);
        this.barcode = contents;
    }
}
