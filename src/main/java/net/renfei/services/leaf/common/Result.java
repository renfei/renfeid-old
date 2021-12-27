package net.renfei.services.leaf.common;

public class Result {
    private long id;
    private StatusEnum statusEnum;

    public Result() {

    }
    public Result(long id, StatusEnum statusEnum) {
        this.id = id;
        this.statusEnum = statusEnum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return statusEnum;
    }

    public void setStatus(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("id=").append(id);
        sb.append(", status=").append(statusEnum);
        sb.append('}');
        return sb.toString();
    }
}
