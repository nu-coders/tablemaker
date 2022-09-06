
# AutoTableMaker 

Auto Table Maker for NU Students to help them create their tables without having any clashes


## Demo
you can see the api in use on our React Website

https://helper.nucoders.dev

to test the api you can use the following url

https://tm.nucoders.dev/api/v1/
###

## API Reference

#### Get all courses

```
  GET /api/v1/getcourses
```
#### Get all course codes

```
  GET /api/v1/getcoursecodes
```
#### Search course by codes

```
  GET /api/v1/findcourse/${wantedCourse}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `wantedCourse`      | `string` | **Required**. course code to search for |

#### Get course sections

```
  GET /api/v1/findcoursesections/${wantedCourse}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `wantedCourse`      | `string` | **Required**. course code to search for |

#### Search courses with array of course codes

```
  GET /api/v1/findcourses/${wantedCourses}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `wantedCourses`      | `array of strings` | **Required**. array of course codes to search for |

#### Create table with array of course codes

```
  GET /api/v1/createtable/${wantedCourses}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `wantedCourses`      | `array of strings` | **Required**. array of course codes to search for |

#### Create table with section constraints

```
  GET /api/v1/constrainedcreatetable/${wantedCourses}&&{constraints}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `wantedCourses`      | `array of strings` | **Required**. array of course codes to search for |
| `constraints`      | `array of strings` | **Required**. array of section number where index |

`constraints` index of each section is the same index for the course code in `wantedCourses`
#### Create table with Day constraints

```
  GET /api/v1/constraineddayscreatetable/${wantedCourses}&${constraints}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `wantedCourses`      | `array of strings` | **Required**. array of course codes to search for |
| `constraints`      | `array of Integers` | **Required**. array of number of days you want to take |




## Authors
- [@NuCoders](https://github.com/nu-coders)
- [@BahaaEldin0](https://www.github.com/BahaaEldin0)
- [@YousefAlsayed](https://github.com/Yusuf-ASM)

