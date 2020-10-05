package com.lb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//博客实体
@Entity
@Table(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;//标题
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;//内容
    private String firstPicture;//博客首图
    private String flag;//标记
    private Integer views;//浏览次数
    private boolean appreciation;//赞赏
    private boolean shareStatement;//转载声明（版权是否开启）
    private boolean commenttabled;//评论
    private boolean published;//是否发布（或保存草稿）
    private boolean recommened;//是否推荐
    @Temporal(TemporalType.TIMESTAMP)//对应在数据库生成的时间
    private Date createTime;//创建时间
    @Temporal(TemporalType.TIMESTAMP)//对应在数据库生成的时间
    private Date updateTime;//修改时间

    @ManyToOne
    private Type type;//一个Blog只有一个Type对象

    @ManyToMany(cascade = {CascadeType.PERSIST})//级联新增
    private List<Tag> tags=new ArrayList<>();//多对多

    //多的一端为关系的维护方，少的一端为关系的被维护一方
    @ManyToOne//关系的维护方
    private User user;//多对一

    //多的一端为关系的维护方，少的一端为关系的被维护一方
    @OneToMany(mappedBy = "blog")//被维护的一方
    private List<Comment> comments=new ArrayList<>();//一对多

    @Transient//不需要保存到数据库
    private String tagIds;
    //对发布的博客描述
    private String description;

    public Blog(){

    }

    public Blog(String title, String content, String firstPicture, String flag, Integer views, boolean appreciation, boolean shareStatement, boolean commenttabled, boolean published, boolean recommened, Date createTime, Date updateTime, Type type, List<Tag> tags, User user, List<Comment> comments, String tagIds, String description) {
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatement = shareStatement;
        this.commenttabled = commenttabled;
        this.published = published;
        this.recommened = recommened;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.type = type;
        this.tags = tags;
        this.user = user;
        this.comments = comments;
        this.tagIds = tagIds;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommenttabled() {
        return commenttabled;
    }

    public void setCommenttabled(boolean commenttabled) {
        this.commenttabled = commenttabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommened() {
        return recommened;
    }

    public void setRecommened(boolean recommened) {
        this.recommened = recommened;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }


    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commenttabled=" + commenttabled +
                ", published=" + published +
                ", recommened=" + recommened +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                ", tagIds='" + tagIds + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
