import { useForm } from 'react-hook-form';

export const CourseForm = ({ course, onSubmit }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <label htmlFor="name">Name</label>
      <input
        type="text"
        id="name"
        name="name"
        defaultValue={course?.name}
        {...register('name', { required: true })}
      />
      {errors.name && <p>A course name is required</p>}

      <label htmlFor="description">Description</label>
      <input
        type="text"
        id="description"
        name="description"
        defaultValue={course?.description}
        {...register('description', { required: true })}
      />
      {errors.description && <p>A course description is required</p>}

      <label htmlFor="code">Code</label>
      <input
        type="text"
        id="code"
        name="code"
        defaultValue={course?.code}
        {...register('code', { required: false })}
      />
      <button type="submit">Submit</button>
    </form>
  );
};
