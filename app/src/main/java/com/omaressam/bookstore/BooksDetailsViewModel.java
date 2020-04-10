package com.omaressam.bookstore;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.omaressam.bookstore.Model.Books;

public class BooksDetailsViewModel extends ViewModel {

    public MutableLiveData<Books> book = new MutableLiveData<>();


}
