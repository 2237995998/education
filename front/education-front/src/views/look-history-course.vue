<template>
    <div>
        <div class="top">
            <el-select v-model="value" placeholder="请选择学期">
                <el-option
                        v-for="item in options"
                        :key="item.termId"
                        :label="item.termName"
                        :value="item.termId">
                </el-option>
            </el-select>
            <el-button type="primary" @click="query()">查询</el-button>
        </div>
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
                    width="90px"
                    prop="course.courseCode"
                    label="课程代码"
                    align="center">
            </el-table-column>

            <el-table-column
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
                    prop="school.schoolName"
                    label="学院"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="teacher.nickname"
                    label="老师"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="course.courseTerm"
                    label="学期"
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
        name: "look-history-course",
        data() {
            return {
                options: [],
                value: 0,
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
                this.$axios.get("/course/history?pageNum=" + this.pageNum + '&pageSize=' + this.pageSize
                    +'&userId=' + this.$store.getters.getUser.userId + '&term=' + this.value,
                    {
                        headers: {
                            "Authorization": this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    this.courseList = data.list;
                    this.total = data.total;
                })
            },
            getTermList() {
                const _this = this;
                this.$axios.get("/term/getAll",
                    {
                        headers: {
                            "Authorization": _this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    _this.options = data.list;
                })
            },
        }
        ,
        created() {
            this.getList();
            this.getTermList();
        }
    }
</script>

<style lang="less" scoped>

    .table {
        margin-top: 20px;
        text-align: center;
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