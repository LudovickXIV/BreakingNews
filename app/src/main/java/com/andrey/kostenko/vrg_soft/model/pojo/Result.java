
package com.andrey.kostenko.vrg_soft.model.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("adx_keywords")
    @Expose
    private String adxKeywords;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("email_count")
    @Expose
    private long emailCount;
    @SerializedName("count_type")
    @Expose
    private String countType;
    @SerializedName("column")
    @Expose
    private Object column;
    @SerializedName("eta_id")
    @Expose
    private long etaId;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("asset_id")
    @Expose
    private long assetId;
    @SerializedName("nytdsection")
    @Expose
    private String nytdsection;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("des_facet")
    @Expose
    private Object desFacet;
    @SerializedName("org_facet")
    @Expose
    private Object orgFacet;
    @SerializedName("per_facet")
    @Expose
    private Object perFacet;
    @SerializedName("geo_facet")
    @Expose
    private Object geoFacet;
    @SerializedName("media")
    @Expose
    private List<Medium> media = new ArrayList<Medium>();
    @SerializedName("uri")
    @Expose
    private String uri;

    private String thumb;

    private Result(Builder builder) {
         this.url = builder.url;
         this.adxKeywords = builder.adxKeywords;
         this.subsection = builder.subsection;
         this.emailCount = builder.emailCount;
         this.countType = builder.countType;
         this.column = builder.column;
         this.etaId = builder.etaId;
         this.section = builder.section;
         this.id = builder.id;
         this.assetId = builder.assetId;
         this.nytdsection = builder.nytdsection;
         this.byline = builder.byline;
         this.type = builder.type;
         this.title = builder.title;
         this._abstract = builder._abstract;
         this.publishedDate = builder.publishedDate;
         this.source = builder.source;
         this.updated = builder.updated;
         this.desFacet = builder.desFacet;
         this.orgFacet = builder.orgFacet;
         this.perFacet = builder.perFacet;
         this.geoFacet = builder.geoFacet;
         this.media = builder.media;
         this.uri = builder.uri;
         this.thumb = builder.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getThumb() {
        return thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public long getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(long emailCount) {
        this.emailCount = emailCount;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public Object getColumn() {
        return column;
    }

    public void setColumn(Object column) {
        this.column = column;
    }

    public long getEtaId() {
        return etaId;
    }

    public void setEtaId(long etaId) {
        this.etaId = etaId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAssetId() {
        return assetId;
    }

    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }

    public String getNytdsection() {
        return nytdsection;
    }

    public void setNytdsection(String nytdsection) {
        this.nytdsection = nytdsection;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Object getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(Object desFacet) {
        this.desFacet = desFacet;
    }

    public Object getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(Object orgFacet) {
        this.orgFacet = orgFacet;
    }

    public Object getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(Object perFacet) {
        this.perFacet = perFacet;
    }

    public Object getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(Object geoFacet) {
        this.geoFacet = geoFacet;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public static class Builder {
        private String url;
        private String adxKeywords;
        private String subsection;
        private long emailCount;
        private String countType;
        private Object column;
        private long etaId;
        private String section;
        private long id;
        private long assetId;
        private String nytdsection;
        private String byline;
        private String type;
        private String title;
        private String _abstract;
        private String publishedDate;
        private String source;
        private String updated;
        private Object desFacet;
        private Object orgFacet;
        private Object perFacet;
        private Object geoFacet;
        private List<Medium> media = new ArrayList<Medium>();
        private String uri;
        private String thumb;

        public Builder() {
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setThumb(String thumb) {
            this.thumb = thumb;
            return this;
        }

        public Builder setAdxKeywords(String adxKeywords) {
            this.adxKeywords = adxKeywords;
            return this;
        }

        public Builder setSubsection(String subsection) {
            this.subsection = subsection;
            return this;
        }

        public Builder setEmailCount(long emailCount) {
            this.emailCount = emailCount;
            return this;
        }

        public Builder setCountType(String countType) {
            this.countType = countType;
            return this;
        }

        public Builder setColumn(Object column) {
            this.column = column;
            return this;
        }

        public Builder setEtaId(long etaId) {
            this.etaId = etaId;
            return this;
        }

        public Builder setSection(String section) {
            this.section = section;
            return this;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setAssetId(long assetId) {
            this.assetId = assetId;
            return this;
        }

        public Builder setNytdsection(String nytdsection) {
            this.nytdsection = nytdsection;
            return this;
        }

        public Builder setByline(String byline) {
            this.byline = byline;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder set_abstract(String _abstract) {
            this._abstract = _abstract;
            return this;
        }

        public Builder setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        public Builder setUpdated(String updated) {
            this.updated = updated;
            return this;
        }

        public Builder setDesFacet(Object desFacet) {
            this.desFacet = desFacet;
            return this;
        }

        public Builder setOrgFacet(Object orgFacet) {
            this.orgFacet = orgFacet;
            return this;
        }

        public Builder setPerFacet(Object perFacet) {
            this.perFacet = perFacet;
            return this;
        }

        public Builder setGeoFacet(Object geoFacet) {
            this.geoFacet = geoFacet;
            return this;
        }

        public Builder setMedia(List<Medium> media) {
            this.media = media;
            return this;
        }

        public Builder setUri(String uri) {
            this.uri = uri;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }

}