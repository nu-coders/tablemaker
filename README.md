
# AutoTableMaker 

Auto Table Maker for NU Students to help them create their tables without having any clashes


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




## Authors
- [@NuCoders](https://github.com/nu-coders)
- [@BahaaEldin0](https://www.github.com/BahaaEldin0)
- [@YousefAlsayed](https://github.com/Yusuf-ASM)

