package com.example.project_1;

public class Group {
    private String GroupName;
    private String GroupDescription;
    private Integer NumOfMembers;
    private String[] MemberList;

    public void setGroupName (String GroupName) {
        this.GroupName = GroupName;
    }

    public String getGroupName () {
        return GroupName;
    }

    public void setGroupDescription (String GroupDescription) {
        this.GroupDescription = GroupDescription;
    }

    public String setGroupDescription() {
        return GroupDescription;
    }

    public void setNumOfMembers (int NumOfMembers) {
        this.NumOfMembers = NumOfMembers;
    }

    public Integer getNumOfMembers () {
        return NumOfMembers;
    }

    public void setMemberList (String[] MemberList) {
        this.MemberList = MemberList;
    }

    public String[] MemberList () {
        return MemberList;
    }
}
