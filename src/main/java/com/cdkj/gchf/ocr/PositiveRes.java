package com.cdkj.gchf.ocr;

import java.util.List;

/**
 * @author old3
 * @title: PositiveRes
 * @description: 正面照片响应
 * @date 2019-05-16 14:23
 */
public class PositiveRes {


    /**
     * address : 地址
     * birth : 出生日期 19800729
     * config_str : {"side":"face"}
     * face_rect : {"angle":-81.99303436279297,"center":{"x":475.26129150390625,"y":475.8135070800781},"size":{"height":183.00001525878906,"width":178.00001525878906}}
     * face_rect_vertices : [{"x":553.47216796875,"y":576.6912231445312},{"x":372.2561950683594,"y":551.2005004882812},{"x":397.0504150390625,"y":374.935791015625},{"x":578.266357421875,"y":400.426513671875}]
     * name : 姓名 潘慧艳
     * nationality : 民族 汉
     * num : 身份证号  332526198007297720
     * request_id : 20190516142302_b7ef93fd20c68d9df8e8364e6018ef01
     * sex : 女
     * success : true
     */

    private String address;

    private String birth;

    private String config_str;

    private FaceRectBean face_rect;

    private String name;

    private String nationality;

    private String num;
    private String request_id;
    private String sex;
    private boolean success;
    private List<FaceRectVerticesBean> face_rect_vertices;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getConfig_str() {
        return config_str;
    }

    public void setConfig_str(String config_str) {
        this.config_str = config_str;
    }

    public FaceRectBean getFace_rect() {
        return face_rect;
    }

    public void setFace_rect(FaceRectBean face_rect) {
        this.face_rect = face_rect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<FaceRectVerticesBean> getFace_rect_vertices() {
        return face_rect_vertices;
    }

    public void setFace_rect_vertices(List<FaceRectVerticesBean> face_rect_vertices) {
        this.face_rect_vertices = face_rect_vertices;
    }

    public static class FaceRectBean {
        /**
         * angle : -81.99303436279297
         * center : {"x":475.26129150390625,"y":475.8135070800781}
         * size : {"height":183.00001525878906,"width":178.00001525878906}
         */

        private double angle;
        private CenterBean center;
        private SizeBean size;

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }

        public CenterBean getCenter() {
            return center;
        }

        public void setCenter(CenterBean center) {
            this.center = center;
        }

        public SizeBean getSize() {
            return size;
        }

        public void setSize(SizeBean size) {
            this.size = size;
        }

        public static class CenterBean {
            /**
             * x : 475.26129150390625
             * y : 475.8135070800781
             */

            private double x;
            private double y;

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }
        }

        public static class SizeBean {
            /**
             * height : 183.00001525878906
             * width : 178.00001525878906
             */

            private double height;
            private double width;

            public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }

            public double getWidth() {
                return width;
            }

            public void setWidth(double width) {
                this.width = width;
            }
        }
    }

    public static class FaceRectVerticesBean {
        /**
         * x : 553.47216796875
         * y : 576.6912231445312
         */

        private double x;
        private double y;

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
