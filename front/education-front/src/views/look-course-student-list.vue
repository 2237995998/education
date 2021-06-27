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
            <h4 align="center">
                打印时间:{{date}}
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
                        prop="username"
                        label="学号"
                        align="center">
                </el-table-column>

                <el-table-column
                        prop="nickname"
                        label="姓名"
                        align="center">
                </el-table-column>

                <el-table-column
                        width="50px"
                        prop="sex"
                        label="性别"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="150px"
                        prop="studentClass"
                        label="班级"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="1"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="2"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="3"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="4"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="5"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="6"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="7"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="8"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="9"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="10"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="11"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="12"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="13"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="14"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="15"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="16"
                        align="center">
                </el-table-column>
                <el-table-column
                        width="40px"
                        prop=""
                        label="17"
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
        name: "look-course-student-list",
        data() {
            return {
                studentList: [],
                course: '',
                teacher: '',
                date: '',
            }
        },
        methods: {
            getList() {
                const _this = this;
                this.$axios.get("/course/showStudentList?courseId=" + _this.$store.getters.getCourseId,
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
            }
        },
        created() {
            this.getList();
            this.getFormatDate();
        }
    }
</script>

<style lang="less" scoped>

    .table {
        width: 1200px;
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