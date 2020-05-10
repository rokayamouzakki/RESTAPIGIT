package main.Connector;

import main.domain.Repo;

import java.util.List;

public class ResponseDto {
    private  long total_count;
    private boolean incomplete_results;
    private List<Repo> items;

    public ResponseDto() {
    }

    public ResponseDto(long total_count, boolean incomplete_results, List<Repo> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }
}
