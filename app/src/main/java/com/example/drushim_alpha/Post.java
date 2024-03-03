package com.example.drushim_alpha;

public class Post
{

    private String Title;
    private String Area;
    private String JobDescription;
    private String JobType;
    private String PostID;
    private String CreatorUID;


    public Post ( String Title , String Area , String JobDescription , String JobType , String PostID , String CreatorUID )
    {

        this.Title = Title;
        this.Area = Area;
        this.JobDescription = JobDescription;
        this.JobType = JobType;
        this.PostID = PostID;
        this.CreatorUID = CreatorUID;


    }

    public String getCreatorUID() {
        return CreatorUID;
    }

    public void setCreatorUID(String creatorUID) {
        CreatorUID = creatorUID;
    }

    public String getPostID() {
        return PostID;
    }

    public void setPostID(String postID) {
        PostID = postID;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
