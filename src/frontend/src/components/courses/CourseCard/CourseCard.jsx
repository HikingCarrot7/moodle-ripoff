import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useRouter } from 'next/router';

export const CourseCard = ({ course }) => {
  const router = useRouter();
  const { name, description } = course;

  const goToCourse = () => {
    router.push(`/courses/${course.id}`);
  };

  const updateCourse = () => {
    router.push(`/courses/update?courseId=${course.id}`);
  };

  return (
    <div className="card flex justify-content-center">
      <Card
        title={name}
        header={<Header />}
        footer={
          <Footer onGoToCourse={goToCourse} onUpdateCourse={updateCourse} />
        }
        className="md:w-25rem">
        <p className="m-0">{description}</p>
      </Card>
    </div>
  );
};

const Header = () => {
  return (
    <img
      alt="Card"
      src="https://primefaces.org/cdn/primereact/images/usercard.png"
    />
  );
};

const Footer = ({ onGoToCourse, onUpdateCourse }) => {
  return (
    <div className="flex flex-wrap justify-content-end gap-2">
      <Button label="Go to course" onClick={onGoToCourse} />
      <Button label="Update course" onClick={onUpdateCourse} />
    </div>
  );
};
