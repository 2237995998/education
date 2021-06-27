<template>
    <div>
        <el-table
                class="table"
                :data="courseList"
                border
                stripe>

            <el-table-column
                    width="50px"
                    label="序号"
                    align="center">
                <template slot-scope="scope">
                    {{(pageNum-1)*pageSize+scope.$index+1}}
                </template>
            </el-table-column>

            <el-table-column
                    width="110px"
                    sortable
                    prop="course.courseCode"
                    label="课程代码"
                    align="center">
            </el-table-column>

            <el-table-column
                    sortable
                    prop="course.courseName"
                    label="课程名称"
                    align="center">
            </el-table-column>

            <el-table-column
                    width="60px"
                    prop="course.courseType"
                    label="性质"
                    align="center">
            </el-table-column>
            <el-table-column
                    width="50px"
                    prop="course.courseCredit"
                    label="学分"
                    align="center">
            </el-table-column>
            <el-table-column
                    width="50px"
                    prop="course.courseClass"
                    label="班号"
                    align="center">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="school.schoolName"
                    label="学院"
                    align="center">
            </el-table-column>
            <el-table-column
                    sortable
                    prop="teacher.nickname"
                    label="老师"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="course.courseTime"
                    label="时间"
                    align="center">
            </el-table-column>
            <el-table-column
                    width="100px"
                    prop="course.coursePlace"
                    label="地点"
                    align="center">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    align="center"
                    width="80">
                <template slot-scope="scope">
                    <el-button @click="showStudentList(scope.row.course.courseId)" type="text" size="small">学生名单
                    </el-button>
                    <br>
                    <el-button @click="insertGrade(scope.row.course.courseId)" type="text" size="small">录入成绩
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination class="page"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="pageNum"
                       :page-sizes="[5, 10, 20, 30]"
                       :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "look-this-start-course",
        data() {
            return {
                courseList: [],
                pageSize: 5,
                total: 100,
                pageNum: 1,
            }
        },
        methods: {
            query() {
                this.getList();
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.getList();
            },
            handleCurrentChange(val) {
                this.pageNum = val;
                this.getList();
            },
            getList() {
                const _this = this;
                this.$axios.get("/course/showThisStart?pageNum=" + _this.pageNum + '&pageSize=' + _this.pageSize
                    + '&userId=' + this.$store.getters.getUser.userId,
                    {
                        headers: {
                            "Authorization": _this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    _this.courseList = data.list;
                    _this.total = data.total;
                })
            },
            showStudentList(val) {
                this.$store.commit("SET_COURSE_ID", val);
                var routeData = this.$router.resolve({ path: '/showCourseStudent'});
                window.open(routeData.href);
            },
            insertGrade(val){
                this.$store.commit("SET_COURSE_ID", val);
                this.$router.push({ path: '/insertGrade'});
            }
        }
        ,
        created() {
            this.getList();
        }
    }
</script>

<style lang="less" scoped>

    .table {
        margin-top: 20px;
        text-align: center;
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