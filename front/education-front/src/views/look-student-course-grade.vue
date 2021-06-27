<template>
    <div>
        <div id="print">
            <h3 align="center">
                课程名称:{{course.courseName}}[课程代码:{{course.courseCode}}]
            </h3>
            <h4 align="center">
                教学班号:{{course.courseClass}}
                学分:{{course.courseCredit}}({{course.courseType}})
                任课老师:{{teacher.nickname}}
            </h4>
            <el-table
                    class="table"
                    :data="studentList"
                    border
                    stripe>

                <el-table-column
                        width="50px"
                        label="序号"
                        align="center">
                    <template slot-scope="scope">
                        {{scope.$index+1}}
                    </template>
                </el-table-column>

                <el-table-column
                        width="100px"
                        prop="student.username"
                        label="学号"
                        align="center">
                </el-table-column>

                <el-table-column
                        width="100px"
                        prop="student.nickname"
                        label="姓名"
                        align="center">
                </el-table-column>

                <el-table-column
                        width="50px"
                        prop="student.sex"
                        label="性别"
                        align="center">
                    <template slot-scope="scope">
                        {{scope.$index+1}}
                    </template>
                </el-table-column>
                <el-table-column
                        width="150px"
                        prop="student.studentClass"
                        label="班级"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="100px"
                        label="平时成绩"
                        align="center">
                    <template slot-scope="scope">
                        <span v-if="!showEdit[scope.$index]">{{scope.row.grade.usualGrade}}</span>
                        <el-input type="text" v-model="scope.row.grade.usualGrade" v-else placeholder="请输入平时成绩"></el-input>
                    </template>
                </el-table-column>
                <el-table-column
                        width="100px"
                        label="期末成绩"
                        align="center">
                    <template slot-scope="scope">
                        <span v-if="!showEdit[scope.$index]">{{scope.row.grade.endGrade}}</span>
                        <el-input type="text" v-model="scope.row.grade.endGrade" v-else placeholder="请输入期末成绩"></el-input>
                    </template>
                </el-table-column>
                <el-table-column
                        width="100px"
                        prop="grade.totalGrade"
                        label="总成绩"
                        align="center">
                </el-table-column>
            </el-table>

        </div>
        <div style="margin-top: 50px; text-align: center">
            <button v-print="'#print'">打印</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "look-student-course-grade",
        data() {
            return {
                showEdit: [],
                studentList: [],
                course: '',
                teacher: '',
                date: '',
            }
        },
        methods: {
            getList() {
                const _this = this;
                this.$axios.get("/grade/showStudentsCourseGrade?courseId=" + _this.$store.getters.getCourseId,
                    {
                        headers: {
                            "Authorization": _this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    _this.studentList = data.list;
                    _this.course = data.course;
                    _this.teacher = data.teacher;
                })
            },
            getFormatDate() {
                var date = new Date();
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                var currentDate = date.getFullYear() + "年" + month + "月" + strDate
                    + "日" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
                this.date = currentDate;
            },
            showUpdate(index) {
                this.showEdit[index] = true;
                this.$set(this.showEdit, index, true); //这里要用$set方法，否则页面状态不更新
            },
            //取消修改
            cancelUpdate(index) {
                this.$confirm("取消修改？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.$set(this.showEdit, index, false);
                        this.getList();
                        this.getTermList();
                        this.getTeacherList();
                    })
                    .catch(() => {
                    });
            },
            submitUpdate(index,row) {
                this.$confirm("确认修改？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.$axios.post("/grade/update", row.grade).then(res => {
                            this.$message({
                                message:res.data.message,
                                type:"success"
                            })
                            this.showEdit[index] = false;
                            this.$set(this.showEdit, index, false);
                            this.getList();
                        })
                    })
                    .catch(() => {
                    });
            },
        },
        created() {
            this.getList();
            this.getFormatDate();
        }
    }
</script>

<style lang="less" scoped>

    .table {
        max-width: 754px;
        margin: auto;
        border: 1px solid black;
    }


    .top {
        margin: 0 auto;
        text-align: center;
    }

    .page {
        margin-top: 15px;
        text-align: center;
    }
</style>